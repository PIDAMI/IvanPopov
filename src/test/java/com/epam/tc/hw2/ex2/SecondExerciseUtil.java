package com.epam.tc.hw2.ex2;

import static com.epam.tc.hw2.ex2.SecondExerciseTest.COLOR_XPATH;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondExerciseUtil extends BaseUtil {



    public static final String SERVICE_BUTTON_CSS_LOCATOR =
        "//*[@class='uui-navigation nav navbar-nav m-l8']//*[contains(text(),'Service')]";
    public static final String DIFFERENT_ELEMENTS_BUTTON_CSS_LOCATOR =
        "//*[@class='uui-navigation nav navbar-nav m-l8']//*[contains(text(),'Different elements')]";
    private static final String COLOR_OPTION_XPATH = "//div[@class='colors']//option";

    public SecondExerciseUtil(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // 5. Open through the header menu Service -> Different Elements Page;
    public void gotoDifferentElementsPage() {
        WebElement serviceButton = driver.findElement(By.xpath(SERVICE_BUTTON_CSS_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(serviceButton));
        serviceButton.click();
        WebElement differentElementsButton = driver.findElement(By.xpath(DIFFERENT_ELEMENTS_BUTTON_CSS_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(differentElementsButton));
        differentElementsButton.click();
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
    public void selectColor(Map<String, String> selectedOptionTextToLogFormat) {
        WebElement form = driver.findElement(By.xpath(COLOR_XPATH));
        form = wait.until(ExpectedConditions.elementToBeClickable(form));
        form.click();

        List<WebElement> options = driver.findElements(By.xpath(COLOR_OPTION_XPATH));
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
    public void checkLogIsShown(String checkboxesXpath,
                                       Map<String, String> checkboxTextToLogFormat) {
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxesXpath));
        checkboxes.stream()
                  .filter(el -> checkboxTextToLogFormat.containsKey(el.getText()))
                  .forEach(el -> {
                      // log is element with text such as listed in map; assert that this log exists
                      String logRowFormat = checkboxTextToLogFormat.get(el.getText());
                      List<WebElement> logRows = driver.findElements(By.xpath(
                          String.format("//*[contains(text(),'%s')]", logRowFormat))
                      );
                      assertThat(logRows.size()).isNotZero();
                  });
    }
}
