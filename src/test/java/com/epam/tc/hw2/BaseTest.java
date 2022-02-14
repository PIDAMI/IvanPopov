package com.epam.tc.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.ex1.entities.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    private static final long TIMEOUT_SECONDS = 10;
    protected final String indexPageURL = "https://jdi-testing.github.io/jdi-light/index.html";
    protected WebDriverWait wait;
    protected final User user = loadUserFromProperties();
    protected final String expectedBrowserTitle = "Home Page";

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    // 12. Close browser
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    // 1. Open test site by URL
    public void gotoSite() {
        if (driver == null) {
            System.out.println("driver is null :(");
            driver = new ChromeDriver();
        }
        driver.get(indexPageURL);
    }

    // 2. Assert Browser title
    public void checkPageTitle(String expectedTitle) {
        String title = driver.getTitle();
        assertThat(title).isEqualTo(expectedTitle);
    }


    // 3. Perform login
    public void login(User user) {
        WebElement dropToggle = driver.findElement(By.className("uui-profile-menu"));
        dropToggle = wait.until(ExpectedConditions.elementToBeClickable(dropToggle));
        dropToggle.click();

        WebElement loginForm = driver.findElement(By.cssSelector("input#name"));
        loginForm.sendKeys(user.getLogin());

        WebElement passwordForm = driver.findElement(By.id("password"));
        passwordForm.sendKeys(user.getPassword());

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    // 4. Assert Username is loggined;
    public void checkUserIsLoggined(User user) {
        WebElement username = driver.findElement(By.cssSelector("span#user-name"));
        assertThat(username.isDisplayed()).isEqualTo(true);
        assertThat(username.getText()).isEqualTo(user.getDisplayedName());
    }

    public User loadUserFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = this
            .getClass()
            .getResourceAsStream("/loginData.properties")) {

            prop.load(inputStream);
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String displayedName = prop.getProperty("displayedName");

            return new User(username, password, displayedName);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
