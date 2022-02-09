package com.epam.tc.hw2.ex1;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstExerciseTest {

    WebDriver driver;
    private static final long EXPLICIT_TIMEOUT_SECONDS = 10;
    private static final String URL = "https://jdi-testing.github.io/jdi-light/index.html";
    WebDriverWait wait;
    private final LoginData loginData = loadLoginDataFromProperties();

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT_SECONDS));
        driver = new ChromeDriver();
        driver.get(URL);
    }

//    @BeforeMethod
//    public void setUp() {
//
//    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Test
    public void checkPageTitle() {
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Home Page");
    }


    @Test
    public void checkCanLogin() {
        WebElement dropToggle = driver.findElement(By.className("dropdown-toggle"));
        dropToggle = wait.until(ExpectedConditions.elementToBeClickable(dropToggle));
        dropToggle.click();


        WebElement loginForm = driver.findElement(By.id("name"));
        loginForm.sendKeys(loginData.getLogin());

        WebElement passwordForm = driver.findElement(By.id("password"));
        passwordForm.sendKeys(loginData.getPassword());

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();


        dropToggle = wait.until(ExpectedConditions.elementToBeClickable(dropToggle));
        dropToggle.click();
        WebElement logoutButton = driver.findElement(By.cssSelector("div.logout > button"));
        assertThat(logoutButton.isDisplayed()).isEqualTo(true);
        //        logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

    }


    private LoginData loadLoginDataFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = this
            .getClass()
            .getResourceAsStream("loginData.properties")) {

            return new LoginData(prop.getProperty("username"),
                prop.getProperty("password"));

        } catch (FileNotFoundException e) {
            System.out.println("Properties file w/ "
                + "login data not found");
        } catch (IOException e) {
            System.out.println("io error while reading from "
                + "login resource file");
        }
        return new LoginData("", "");
    }

    private class LoginData {
        private final String login;
        private final String password;

        public LoginData(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

}
