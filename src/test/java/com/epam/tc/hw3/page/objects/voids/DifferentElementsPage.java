package com.epam.tc.hw3.page.objects.voids;

import static com.epam.tc.hw3.AbstractBaseTest.TIMEOUT_SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.page.objects.composite.components.BottomPartComponent;
import com.epam.tc.hw3.page.objects.composite.components.HeaderComponent;
import com.epam.tc.hw3.page.objects.composite.components.ProfileComponent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DifferentElementsPage {

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

    @FindBy(xpath = "//*[contains(text(),'Colors: value changed to Yellow')]")
    private WebElement colorLog;

    @FindBy(xpath = "//*[contains(text(),'condition changed to true')]")
    private List<WebElement> elementsCheckboxesLog;

    @FindBy(xpath = "//*[contains(text(),'Selen')]")
    private List<WebElement> radioCheckboxesLog;


    public DifferentElementsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void selectCheckboxes(List<WebElement> allCheckboxes,
                                 List<String> neededCheckboxesText) {
        allCheckboxes.stream()
                  .filter(el -> neededCheckboxesText.contains(el.getText()))
                  .forEach(el -> {
                      WebElement inputForm = el.findElement(By.xpath("./input"));
                      wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                      assertThat(inputForm.isSelected()).isEqualTo(true);
                  });
    }

    public void selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        selectCheckboxes(elementsCheckboxes, checkboxesText);
    }

    public void selectRadioCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        selectCheckboxes(radioCheckboxes, checkboxesText);
    }

    public Boolean selectColorAndCheckIfSelected(String color) {
        colorForm.click();
        colorOptions.stream()
            .filter(el -> color.equals(el.getText()))
            .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        String checkedOption = new Select(colorForm).getFirstSelectedOption().getText();
//        assertThat(checkedOption).isEqualTo(color);
        return checkedOption.equals(color);
    }

    public List<String> getLogIfDisplayed(List<WebElement> logElements, List<String> checkboxText) {
        List<String> result = new ArrayList<>();
        checkboxText = List.copyOf(checkboxText);
        for (WebElement logRow : logElements) {
            if (checkboxText.contains(logRow.getText()) && logRow.isDisplayed()) {
                result.add(logRow.getText());
            }
        }
        return result;
    }

    public Optional<String> getColorLogIfDisplayed(String color) {
        List<String> log = getLogIfDisplayed(List.of(colorLog), List.of(color));
        return log.isEmpty() ? Optional.empty() : Optional.of(log.get(0));
    }

    public List<String> getElementsCheckboxIfDisplayed(List<String> checkboxesText) {
        return getLogIfDisplayed(elementsCheckboxesLog, checkboxesText);
    }

    public List<String> getRadioCheckboxIfDisplayed(List<String> checkboxesText) {
        return getLogIfDisplayed(radioCheckboxesLog, checkboxesText);
    }


}
