package com.epam.tc.hw5.hooks;

import com.epam.tc.hw5.context.TestContext;
import com.epam.tc.hw5.entities.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CucumberHook {
    public static final long TIMEOUT_SECONDS = 10;
    private WebDriver driver;

    @BeforeAll()
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Before()
    public void setUp() {
        Map<String, User> users = new HashMap<>();
        User user = loadUserFromProperties();
        users.put(user.getName(), user);
        TestContext.getInstance().setObject("nameToUserMap", users);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        TestContext.getInstance().setObject("driver", driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        TestContext.getInstance().setObject("wait", wait);
    }

    @After()
    public void tearDown(Scenario scenario) {
        driver.quit();
        TestContext.getInstance().cleanContext();
    }

    public static User loadUserFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = CucumberHook.class
            .getResourceAsStream("/loginData.properties")) {

            prop.load(inputStream);
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String name = prop.getProperty("name");
            return new User(username, password, name);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
