package com.epam.tc.hw4;

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
    public static final long TIMEOUT_SECONDS = 10;
    protected WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setUp(ITestContext context) {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    // 12. Close browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
