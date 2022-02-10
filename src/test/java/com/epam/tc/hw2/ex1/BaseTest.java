package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.ex1.entities.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    private static final long TIMEOUT_SECONDS = 10;
    // can hardcode url - css locators are specific to particular site anyway
    // if we want to change site, will have to change locators as well
    private static final String SITE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    protected WebDriverWait wait;
    protected final User loginData = loadUserFromProperties();

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(SITE_URL);
    }

    @AfterTest
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
            if (username == null || password == null) {
                throw new InvalidArgumentException("Cant load login data from prop file - username or pass is null");
            }

            return new User(username, password);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
