package com.epam.tc.hw2.ex2;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseTest;
import com.epam.tc.hw2.BaseUtil;
import com.epam.tc.hw2.entities.User;
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
    public static final String CHECKBOX_ELEMENTS_XPATH = "//label[@class='label-checkbox']";
    public static final String CHECKBOX_RADIO_XPATH = "//label[@class='label-radio']";
    public static final String COLOR_XPATH = "//div[@class='colors']/*";


    @Test(dataProvider = "second exercise data",
          dataProviderClass = SecondExerciseData.class)
    public void secondExerciseTest(String url, String expectedBrowserTitle,
                                   User user, Map<String, String> checkboxElementsTextToLogFormat,
                                   Map<String, String> checkboxRadioTextToLogFormat,
                                   Map<String, String> colorTextToLogFormat) {

        SecondExerciseUtil util = new SecondExerciseUtil(driver, wait);

        util.gotoSite(url);
        util.checkPageTitle(expectedBrowserTitle);
        util.login(user);
        util.checkUserIsLoggined(user);
        util.gotoDifferentElementsPage();
        util.selectCheckboxByTextAndAssertItsChecked(CHECKBOX_ELEMENTS_XPATH, checkboxElementsTextToLogFormat);
        util.selectCheckboxByTextAndAssertItsChecked(CHECKBOX_RADIO_XPATH, checkboxRadioTextToLogFormat);
        util.selectColor(colorTextToLogFormat);
        util.checkLogIsShown(CHECKBOX_ELEMENTS_XPATH, checkboxElementsTextToLogFormat);
        util.checkLogIsShown(CHECKBOX_RADIO_XPATH, checkboxRadioTextToLogFormat);
        util.checkLogIsShown(COLOR_XPATH, colorTextToLogFormat);
    }





}
