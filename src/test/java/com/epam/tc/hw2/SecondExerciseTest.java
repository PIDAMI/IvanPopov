package com.epam.tc.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class SecondExerciseTest extends BaseTest {

    private final String colorOption = "Yellow";

    @Test
    public void secondExerciseTest() {
        gotoSite();
        checkPageTitle(expectedBrowserTitle);
        login(user);
        checkUserIsLoggined(user);
        gotoDifferentElementsPage();
        selectCheckboxes();
        selectRadio();
        selectColor(colorOption);
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

    public void selectCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(By.xpath(
            "//label[type='label-checkbox']/*")
        );
        List<String> expectedCheckboxText = List.of("Water", "Wind");
        checkboxes.stream()
                  .filter(el -> expectedCheckboxText.contains(el.getText()))
                  .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

    }

    public void selectRadio() {
        List<WebElement> radios = driver.findElements(By.xpath(
            "//label[type='label-radio']/*")
        );
        String expectedRadioName = "Selen";
        radios.stream()
              .filter(el -> expectedRadioName.equals(el.getText()))
              .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());
    }

    // https://developer.mozilla.org/ru/docs/Web/HTML/Element/select
    // selected option is marked w/ 'selected' tag
    public void selectColor(String colorOption) {
        WebElement form = driver.findElement(By.xpath("//div[@class='colors']/*"));
        form = wait.until(ExpectedConditions.elementToBeClickable(form));
        form.click();
        List<WebElement> options = driver.findElements(By.xpath("//div[class='colors']//option"));
        options.stream()
                  .filter(el -> colorOption.equals(el.getText()))
                  .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());
    }

}
