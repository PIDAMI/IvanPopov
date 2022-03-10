package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.BaseTest;
import org.testng.annotations.Test;

public class FirstExerciseTest extends BaseTest {

    @Test()
    public void firstExerciseTest() {
        FirstExerciseUtil util = new FirstExerciseUtil(driver, wait);
        FirstExerciseData data = new FirstExerciseData();

        util.gotoSite(data.getIndexPageUrl());
        util.checkPageTitle(data.getExpectedBrowserTitle());
        util.login(data.getUser());
        util.checkUserIsLoggined(data.getUser());
        util.checkAllHeadersItemsDisplayed(data.getExpectedHeaderButtonsText());
        util.checkImagesDisplayedAndHaveTextBelow(data.getImageCssLocatorToTextAfterImage());
        util.checkFrameWithButtonExists(data.getFrameButtonValue());
        util.checkLeftSectionHasItemsWithProperText(data.getExpectedLeftSectionItemsText());
    }
}
