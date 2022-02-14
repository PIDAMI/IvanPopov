package com.epam.tc.hw2.ex2;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SecondExerciseTest extends BaseTest {


    // path not to input node but to its parent which has text
    private final String checkboxElementsXpath = "//label[@class='label-checkbox']";
    private final String checkboxRadioXpath = "//label[@class='label-radio']";
    private final String colorXpath = "//div[@class='colors']/*";


    private final Map<String, String> checkboxElementsTextToLogFormat = Map.ofEntries(
        entry("Water", "Water: condition changed to true"),
        entry("Wind", "Wind: condition changed to true")
    );

    private final Map<String, String> checkboxRadioTextToLogFormat = Map.ofEntries(
        entry("Selen", "Selen")
    );

    private final Map<String, String> colorTextToLogFormat = Map.ofEntries(
        entry("Yellow", "Colors: value changed to Yellow")
    );

    @Test
    public void secondExerciseTest() {
        gotoSite();
        checkPageTitle(expectedBrowserTitle);
        login(user);
        checkUserIsLoggined(user);
        gotoDifferentElementsPage();
        selectCheckboxByTextAndAssertItsChecked(checkboxElementsXpath, checkboxElementsTextToLogFormat);
        selectCheckboxByTextAndAssertItsChecked(checkboxRadioXpath, checkboxRadioTextToLogFormat);
        selectColor(colorXpath, colorTextToLogFormat);
        checkLogIsShown(checkboxElementsXpath, checkboxElementsTextToLogFormat);
        checkLogIsShown(checkboxRadioXpath, checkboxRadioTextToLogFormat);
        checkLogIsShown(colorXpath, colorTextToLogFormat);
    }


    // 5. Open through the header menu Service -> Different Elements Page;
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


    // 6. Select checkboxes OR
    // 7. Select radio (both done by this method w/ different arguments);
    // somehow filtering by text in xpath doesn't work
    // so all children are filtered manually
    public void selectCheckboxByTextAndAssertItsChecked(String checkboxesXpath,
                                                        Map<String, String> checkboxTextToLogFormat) {
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxesXpath));
        checkboxes.stream()
                  .filter(el -> checkboxTextToLogFormat.containsKey(el.getText()))
                  .forEach(el -> {
                      WebElement inputForm = el.findElement(By.xpath("./input"));
                      wait.until(ExpectedConditions.elementToBeClickable(inputForm)).click();
                      assertThat(inputForm.isSelected()).isEqualTo(true);
                  });
    }


    // 8. Select in dropdown;
    public void selectColor(String colorXpath, Map<String, String> selectedOptionTextToLogFormat) {
        WebElement form = driver.findElement(By.xpath(colorXpath));
        form = wait.until(ExpectedConditions.elementToBeClickable(form));
        form.click();

        List<WebElement> options = driver.findElements(By.xpath("//div[@class='colors']//option"));
        options.stream()
               .filter(el -> selectedOptionTextToLogFormat.containsKey(el.getText()))
               .forEach(el -> wait.until(ExpectedConditions.elementToBeClickable(el)).click());

        String checkedOption = new Select(form).getFirstSelectedOption().getText();
        assertThat(checkedOption).isEqualTo(new ArrayList<>(selectedOptionTextToLogFormat.keySet()).get(0));
    }


    // 9. Assert that
    // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
    // for radio button there is a log row and value is corresponded to the status of radio button
    // for dropdown there is a log row and value is corresponded to the selected value.;
    public void checkLogIsShown(String checkboxesXpath, Map<String, String> checkboxTextToLogFormat) {
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxesXpath));
        checkboxes.stream()
                  .filter(el -> checkboxTextToLogFormat.containsKey(el.getText()))
                  .forEach(el -> {
                      // log is element with text such as listed in map; assert that this log exists
                      String logRowFormat = checkboxTextToLogFormat.get(el.getText());

                      List<WebElement> logRows = driver.findElements(By.xpath(
                          String.format("//*[contains(text(),'%s')]", logRowFormat))
                      );
                      assertThat(logRows.size()).isNotEqualTo(0);
                  });
    }


}
