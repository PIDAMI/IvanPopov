package com.epam.tc.hw3.page.objects.fluents;

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

public class DifferentElementsFluentPage {

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

    public DifferentElementsFluentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    private DifferentElementsFluentPage selectCheckboxesAndCheckIfSelected(List<WebElement> allCheckboxes,
                                                                           List<String> neededCheckboxesText) {
        allCheckboxes.stream()
                     .filter(el -> neededCheckboxesText.contains(el.getText()))
                     .forEach(el -> {
                         WebElement inputForm = el.findElement(By.xpath("./input"));
                         wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                         assertThat(inputForm.isSelected()).isTrue();
                     });
        return this;
    }

    public DifferentElementsFluentPage selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        return selectCheckboxesAndCheckIfSelected(elementsCheckboxes, checkboxesText);
    }

    public DifferentElementsFluentPage selectRadioCheckboxesAndCheckIfSelected(String checkboxesText) {
        return selectCheckboxesAndCheckIfSelected(radioCheckboxes, List.of(checkboxesText));
    }

    public DifferentElementsFluentPage selectColorAndCheckIfSelected(String color) {
        colorForm.click();
        colorOptions.stream()
                    .filter(el -> color.equals(el.getText()))
                    .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        String checkedOption = new Select(colorForm).getFirstSelectedOption().getText();
        assertThat(checkedOption).isEqualTo(color);
        return this;
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
