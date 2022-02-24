package com.epam.tc.hw3.page.objects.voids;

import static com.epam.tc.hw3.AbstractBaseTest.TIMEOUT_SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsVoidPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//label[@class='label-checkbox']")
    private List<WebElement> elementsCheckboxes;

    @FindBy(xpath = "//label[@class='label-radio']")
    private List<WebElement> radioCheckboxes;

    @FindBy(xpath = "//div[@class='colors']/*")
    private WebElement colorForm;

    @FindBy(xpath = "//div[@class='colors']//option")
    private List<WebElement> colorOptions;

    @FindBy(xpath = "//*[contains(text(),'Colors: value changed to')]")
    private WebElement colorLog;

    @FindBy(xpath = "//*[contains(text(),'condition changed to true')]")
    private List<WebElement> elementsCheckboxesLog;

    @FindBy(xpath = "//*[contains(text(),'Selen')]")
    private List<WebElement> radioCheckboxesLog;

    public DifferentElementsVoidPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    private void selectCheckboxesAndCheckIfSelected(List<WebElement> allCheckboxes,
                                                                           List<String> neededCheckboxesText) {
        allCheckboxes.stream()
                     .filter(el -> neededCheckboxesText.contains(el.getText()))
                     .forEach(el -> {
                         WebElement inputForm = el.findElement(By.xpath("./input"));
                         wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                         assertThat(inputForm.isSelected()).isTrue();
                     });
    }

    public void selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        selectCheckboxesAndCheckIfSelected(elementsCheckboxes, checkboxesText);
    }

    public void selectRadioCheckboxesAndCheckIfSelected(String checkboxesText) {
        selectCheckboxesAndCheckIfSelected(radioCheckboxes, List.of(checkboxesText));
    }

    public void selectColorAndCheckIfSelected(String color) {
        colorForm.click();
        colorOptions.stream()
                    .filter(el -> color.equals(el.getText()))
                    .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        String checkedOption = new Select(colorForm).getFirstSelectedOption().getText();
        assertThat(checkedOption).isEqualTo(color);
    }

    private List<String> getLogIfDisplayed(List<WebElement> logElements, List<String> checkboxText) {
        List<String> result = new ArrayList<>();
        for (WebElement logRow : logElements) {
            if (logMatchesCheckedOptions(checkboxText, logRow.getText()) && logRow.isDisplayed()) {
                result.add(logRow.getText());
            }
        }
        return result;
    }

    public boolean logMatchesCheckedOptions(List<String> checkedOptions, String actualLog) {
        return checkedOptions.stream().anyMatch(actualLog::contains);
    }

    public List<String> getColorLogIfDisplayed(String color) {
        return getLogIfDisplayed(List.of(colorLog), List.of(color));
    }

    public List<String> getElementsCheckboxLogIfDisplayed(List<String> checkboxesText) {
        return getLogIfDisplayed(elementsCheckboxesLog, checkboxesText);
    }

    public List<String> getRadioCheckboxLogIfDisplayed(String checkboxesText) {
        return getLogIfDisplayed(radioCheckboxesLog, List.of(checkboxesText));
    }
}
