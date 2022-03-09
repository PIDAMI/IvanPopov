package com.epam.tc.hw5;

import com.epam.tc.hw5.hooks.CucumberHook;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractBaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setUp(ITestContext context) {
        ChromeOptions options = new ChromeOptions();
        // THIS OPTION ENABLES ATTACHMENTS IN ALLURE REPORTS
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
    }

    // 12. Close browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
