package com.epam.tc.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SecondExerciseTest extends BaseTest {

    @Test
    public void secondExerciseTest() {
        gotoSite();
        checkPageTitle(expectedBrowserTitle);
        login(user);
        checkUserIsLoggined(user);
        gotoDifferentElementsPage();
    }

    public void gotoDifferentElementsPage() {
        String serviceButtonLocator = "//*[@class='uui-navigation nav navbar-nav m-l8']"
            + "//*[contains(text(),'Service')]";
        WebElement serviceButton = driver.findElement(By.xpath(serviceButtonLocator));
        wait.until(ExpectedConditions.elementToBeClickable(serviceButton));
        serviceButton.click();
        String differentElementsButtonLocator = "//*[@class='uui-navigation nav navbar-nav m-l8']"
            + "//*[contains(text(),'Different elements')]";
        WebElement differentElementsButton = driver.findElement(By.xpath(differentElementsButtonLocator));
        wait.until(ExpectedConditions.elementToBeClickable(differentElementsButton));
        differentElementsButton.click();
        assertThat(driver.getCurrentUrl()).isNotEqualTo(indexPageURL);
    }

    public void qwe() {
        WebElement waterCheckbox = driver.findElement(By.xpath(
            "//input[@type='checkbox' text()='Water']")
        );
        System.out.println(waterCheckbox.);
    }
}
