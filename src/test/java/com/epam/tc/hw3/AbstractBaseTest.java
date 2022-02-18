package com.epam.tc.hw3;

import com.epam.tc.hw3.entities.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractBaseTest {

    public static final long TIMEOUT_SECONDS = 10L;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final User user = loadUserFromProperties();

    private static final String SITE_URL =
        "https://jdi-testing.github.io/jdi-light/index.html";

    @BeforeSuite
    public void beforeSuit() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        driver.get(SITE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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
