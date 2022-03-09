package com.epam.tc.hw5.page.objects.voids;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
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

public class DifferentElementsVoidPage extends AbstractBaseVoidPage {

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

    public DifferentElementsVoidPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    private void checkSelectableAndAssertIfSelected(List<WebElement> allCheckboxes,
                                                    List<String> neededCheckboxesText) {
        allCheckboxes.stream()
                     .filter(el -> neededCheckboxesText.contains(el.getText()))
                     .forEach(el -> {
                         WebElement inputForm = el.findElement(By.xpath("./input"));
                         wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                         assertThat(inputForm.isSelected()).isTrue();
                     });

    }

    @Step("Selecting elements checkboxes with values {checkboxesText}")
    public void selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        checkSelectableAndAssertIfSelected(elementsCheckboxes, checkboxesText);
    }

    @Step("Selecting radio checkboxes with value {checkboxesText}")
    public void selectRadioButtonAndCheckIfSelected(String checkboxesText) {
        checkSelectableAndAssertIfSelected(radioCheckboxes, List.of(checkboxesText));
    }

    @Step("Selecting color option with value {color}")
    public void selectColorAndCheckIfSelected(String color) {
        colorForm.click();
        colorOptions.stream()
            .filter(el -> color.equals(el.getText()))
            .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        WebElement option = new Select(colorForm).getFirstSelectedOption();
        assertThat(option.getText()).isEqualTo(color);
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
    public List<String> getColorLogIfDisplayed(final String color) {
        return getLogIfDisplayed(List.of(colorLog), List.of(color));
    }

    @Step("Getting elements checkboxes' log with values {checkboxesText}")
    public List<String> getElementsCheckboxLogIfDisplayed(List<String> checkboxesText) {
        return getLogIfDisplayed(elementsCheckboxesLog, checkboxesText);
    }

    @Step("Getting radio checkboxes' log with values {checkboxesText}")
    public List<String> getRadioButtonsLogIfDisplayed(final String buttonsText) {
        return getLogIfDisplayed(radioCheckboxesLog, List.of(buttonsText));
    }
}
