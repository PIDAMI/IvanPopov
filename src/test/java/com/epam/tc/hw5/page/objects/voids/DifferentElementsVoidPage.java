package com.epam.tc.hw5.page.objects.voids;

import static com.epam.tc.hw5.AbstractBaseTest.TIMEOUT_SECONDS;

import io.qameta.allure.Step;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    private List<Boolean> selectCheckboxes(List<WebElement> allCheckboxes,
                                 List<String> neededCheckboxesText) {
        return allCheckboxes.stream()
                            .filter(el -> neededCheckboxesText.contains(el.getText()))
                            .map(el -> {
                                WebElement inputForm = el.findElement(By.xpath("./input"));
                                wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                                return inputForm.isSelected();
                            })
                            .collect(Collectors.toList());
    }

    @Step("Selecting elements checkboxes with values {checkboxesText}")
    public List<Boolean> selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        return selectCheckboxes(elementsCheckboxes, checkboxesText);
    }

    @Step("Selecting radio checkboxes with value {checkboxesText}")
    public Boolean selectRadioCheckboxesAndCheckIfSelected(String checkboxesText) {
        return selectCheckboxes(radioCheckboxes, List.of(checkboxesText)).get(0);
    }

    @Step("Selecting color option with value {color}")
    public Boolean selectColorAndCheckIfSelected(String color) {
        colorForm.click();
        colorOptions.stream()
            .filter(el -> color.equals(el.getText()))
            .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        String checkedOption = new Select(colorForm).getFirstSelectedOption().getText();
        return checkedOption.equals(color);
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

    @Step("Getting color option log with value {value}")
    public Optional<String> getColorLogIfDisplayed(String color) {
        List<String> log = getLogIfDisplayed(List.of(colorLog), List.of(color));
        return log.isEmpty() ? Optional.empty() : Optional.of(log.get(0));
    }

    @Step("Getting elements checkboxes' log with values {checkboxesText}")
    public List<String> getElementsCheckboxLogIfDisplayed(List<String> checkboxesText) {
        return getLogIfDisplayed(elementsCheckboxesLog, checkboxesText);
    }

    @Step("Getting radio checkboxes' log with values {checkboxesText}")
    public Optional<String> getRadioCheckboxLogIfDisplayed(String checkboxesText) {
        List<String> log = getLogIfDisplayed(radioCheckboxesLog, List.of(checkboxesText));
        return log.isEmpty() ? Optional.empty() : Optional.of(log.get(0));
    }
}
