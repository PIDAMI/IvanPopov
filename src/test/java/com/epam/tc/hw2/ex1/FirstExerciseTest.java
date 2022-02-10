package com.epam.tc.hw2.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class FirstExerciseTest extends BaseTest {

    private WebElement dropToggle;
    private final List<String> headerButtonsExpectedText = Stream
        .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
        .sorted()
        .collect(Collectors.toList());
    private final int numberOfImagesOnPage = 4;

    @Test
    public void checkPageTitle() {
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Home Page");
    }



    // 4. Assert Username is loggined
    @Test
    public void checkUsernameIsLoggined() {
        login();
//        WebElement logoutButton = driver.findElement(By.cssSelector("div.logout > button"));
//        assertThat(logoutButton.isDisplayed()).isEqualTo(true);
        WebElement username = driver.findElement(By.cssSelector("span#user-name"));
        assertThat(username.isDisplayed()).isEqualTo(true);
        assertThat(username.getText()).isEqualTo(user.getDisplayedName());
    }

    // 5. Assert that there are 4 items on the header section
    // are displayed and they have proper texts
    @Test
    public void checkAllHeadersItemsDisplayed() {
        login();
        List<WebElement> headerNavigationButtons = driver.findElements(
            By.cssSelector(".uui-navigation.nav.navbar-nav > *")
        );
        assertThat(headerNavigationButtons.size()).isEqualTo(headerButtonsExpectedText.size());
        List<String> headerButtonsActualText = headerNavigationButtons
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());

        assertThat(headerButtonsActualText).isEqualTo(headerButtonsExpectedText);
        for (WebElement button : headerNavigationButtons) {
            assertThat(button.isDisplayed()).isEqualTo(true);
        }
    }

    // Assert that there are 4 images on the Index Page
    // and they are displayed
    @Test
    public void checkNumberOfImages() {
        login();
        List<WebElement> images = driver.findElements(By.cssSelector("img[src]"));
        assertThat(images.size()).isEqualTo(numberOfImagesOnPage);
//        for (WebElement image : images) {
//            Boolean isImageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
//                "return arguments[0].complete "
//                    + "&& typeof arguments[0].naturalWidth != \"undefined\" "
//                    + "&& arguments[0].naturalWidth > 0",
//                image);
//            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return arguments[0].complete", image));
//            assertThat(image.isDisplayed()).isEqualTo(true);
//        }
    }

    // 3. Perform login
    public void login() {
        dropToggle = driver.findElement(By.className("uui-profile-menu"));
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




}
