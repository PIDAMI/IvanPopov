package com.epam.tc.hw2.ex2;

import com.epam.tc.hw2.BaseTest;
import org.testng.annotations.Test;

public class SecondExerciseTest extends BaseTest {

    @Test()
    public void secondExerciseTest() {
        SecondExerciseUtil util = new SecondExerciseUtil(driver, wait);
        SecondExerciseData data = new SecondExerciseData();

        util.gotoSite(data.getIndexPageUrl());
        util.checkPageTitle(data.getExpectedBrowserTitle());
        util.login(data.getUser());
        util.checkUserIsLoggined(data.getUser());
        util.gotoDifferentElementsPage();
        util.selectElementsCheckboxByTextAndAssertItsChecked(data.getCheckboxElementsTextToLogFormat());
        util.selectRadioCheckboxByTextAndAssertItsChecked(data.getCheckboxRadioTextToLogFormat());
        util.selectColor(data.getColorTextToLogFormat());
        util.checkElementsCheckboxLogIsShown(data.getCheckboxElementsTextToLogFormat());
        util.checkRadioCheckboxLogIsShown(data.getCheckboxRadioTextToLogFormat());
        util.checkColorsCheckboxLogIsShown(data.getColorTextToLogFormat());
    }
}
