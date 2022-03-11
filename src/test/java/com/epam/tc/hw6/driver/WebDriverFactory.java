package com.epam.tc.hw6.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverFactory {

    private static final String LOCAL_LAUNCH_TYPE = "local";
    private static final String REMOTE_LAUNCH_TYPE = "remote";
    private static final String NODE_URL = "http://localhost:4444/wd/hub";
    private static final String BROWSER_EXCEPTION_TEXT = "unsupported browser; supported are: "
        + Arrays.stream(Browser.values())
                .map(Browser::toString)
                .reduce((x, y) -> x + ", " + y);


    enum Browser {
        CHROME,
        FIREFOX
    }

    public static WebDriver createDriver(final String launchType, final Browser browser) {
        WebDriver driver;

        if (LOCAL_LAUNCH_TYPE.equalsIgnoreCase(launchType)) {
            driver = createLocalDriver(browser);
        } else if (REMOTE_LAUNCH_TYPE.equalsIgnoreCase(launchType)) {
            driver = createRemoteDriver(browser);
        } else {
            throw new RuntimeException("unknown launch type; supported are: "
                + LOCAL_LAUNCH_TYPE + " " + REMOTE_LAUNCH_TYPE);
        }

        return driver;
    }

    // LOCAL
    private static WebDriver createLocalDriver(final Browser browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                driver = createLocalChromeDriver();
                break;
            case FIREFOX:
                driver = createLocalFirefoxDriver();
                break;
            default:
                throw new RuntimeException(BROWSER_EXCEPTION_TEXT);
        }

        return driver;
    }

    private static WebDriver createLocalChromeDriver() {
        return new ChromeDriver((ChromeOptions) getDefaultChromeCapabilites());
    }

    private static WebDriver createLocalFirefoxDriver() {
        return new FirefoxDriver((FirefoxOptions) getDefaultFirefoxCapabilites());
    }

    // REMOTE
    private static WebDriver createRemoteDriver(final Browser browser) {
        Capabilities capabilities;
        switch (browser) {
            case CHROME:
                capabilities = createRemoteChromeCapabilities();
                break;
            case FIREFOX:
                capabilities = createRemoteFirefoxCapabilities();
                break;
            default:
                throw new RuntimeException(BROWSER_EXCEPTION_TEXT);
        }

        WebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL(NODE_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }

        return driver;
    }

    private static Capabilities createRemoteChromeCapabilities() {
        return getDefaultChromeCapabilites();
    }

    private static Capabilities createRemoteFirefoxCapabilities() {
        return getDefaultFirefoxCapabilites();
    }

    private static Capabilities getDefaultChromeCapabilites() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions capabilities = new ChromeOptions();
        // THIS OPTION ENABLES ATTACHMENTS IN ALLURE REPORTS
        capabilities.addArguments("--disable-dev-shm-usage");
        return capabilities;
    }

    private static Capabilities getDefaultFirefoxCapabilites() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions capabilities = new FirefoxOptions();
        // THIS OPTION ENABLES ATTACHMENTS IN ALLURE REPORTS
        capabilities.addArguments("--disable-dev-shm-usage");
        return capabilities;
    }
}

