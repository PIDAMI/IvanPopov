package com.epam.tc.hw2.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class FirstExerciseTest extends BaseTest {

    private WebElement dropToggle;

    @Test
    public void checkPageTitle() {
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Home Page");
    }



    @Test
    public void checkCanLogin() {
        login();
        WebElement logoutButton = driver.findElement(By.cssSelector("div.logout > button"));
        assertThat(logoutButton.isDisplayed()).isEqualTo(true);
    }




    public void login() {
        dropToggle = driver.findElement(By.className("uui-profile-menu"));
        dropToggle = wait.until(ExpectedConditions.elementToBeClickable(dropToggle));
        dropToggle.click();

        WebElement loginForm = driver.findElement(By.cssSelector("input#name"));
        loginForm.sendKeys(loginData.getLogin());

        WebElement passwordForm = driver.findElement(By.id("password"));
        passwordForm.sendKeys(loginData.getPassword());

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }




}
