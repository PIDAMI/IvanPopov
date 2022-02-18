package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.BaseTest;
import com.epam.tc.hw2.entities.User;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;

public class FirstExerciseTest extends BaseTest {

    @Test(dataProvider = "first exercise data", dataProviderClass = FirstExerciseData.class)
    public void firstExerciseTest(final String url, final String expectedBrowserTitle, User user,
                                  Map<String, String> imageCssLocatorToTextAfterImage,
                                  List<String> expectedHeaderButtonsText,
                                  List<String> expectedLeftSectionItemsText,
                                  String frameButtonValue) {
        FirstExerciseUtil util = new FirstExerciseUtil(driver, wait);

        util.gotoSite(url);
        util.checkPageTitle(expectedBrowserTitle);
        util.login(user);
        util.checkUserIsLoggined(user);
        util.checkAllHeadersItemsDisplayed(expectedHeaderButtonsText);
        util.checkImagesDisplayedAndHaveTextBelow(imageCssLocatorToTextAfterImage);
        util.checkFrameWithButtonExists(frameButtonValue);
        util.checkLeftSectionHasItemsWithProperText(expectedLeftSectionItemsText);
    }



}
