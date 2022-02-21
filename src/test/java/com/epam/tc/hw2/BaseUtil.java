package com.epam.tc.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.entities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtil {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseUtil(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 1. Open test site by URL
    public void gotoSite(String pageUrl) {
        driver.get(pageUrl);
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
        assertThat(username.isDisplayed()).isTrue();
    }
}
