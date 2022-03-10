package com.epam.tc.hw6.ex1;

import com.epam.tc.hw4.AbstractBaseTest;
import com.epam.tc.hw4.entities.User;
import com.epam.tc.hw4.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Homework 4")
@Story("Exercise 1 test")
public class FirstExerciseTest extends AbstractBaseTest {

    @Test()
    public void firstExerciseWithVoidPagesTest() {

        FirstExerciseData data = new FirstExerciseData();
        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        FirstExerciseUtil util = new FirstExerciseUtil(driver, wait, indexPage);
        // 2. Assert Browser title
        util.checkIfTitleCorrect(data.getExpectedBrowserTitle());

        // 3. Perform login
        User user = data.getUser();
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        util.checkIfUserLoggedIn(user);

        // 5. Assert that there are 4 items on the header section
        // are displayed and they have proper texts
        util.checkIfHeaderItemsHaveText(data.getExpectedHeaderButtonsText());

        // 6. Assert that there are 4 images
        // on the Index Page and they are displayed
        // 7. Assert that there are 4 texts on
        // the Index Page under icons and they have proper text
        util.checkIfImagesDisplayedAndHaveProperTextBelow(data.getExpectedTextAfterImages());

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that
        // there is “Frame Button” in the iframe;
        // there's noway to check if frame w/ button exists but to
        // switch to every frame and check if there's a button in some frame
        // so making sure 8 is true necessarily implies that actions in 9 are already done
        indexPage.switchToFrameWithButton(data.getFrameButtonValue());

        // 10. Switch to original window back
        indexPage.switchToMainWindow();

        // 11. Assert that there are 5 items in the
        // Left Section are displayed and they have proper text
        util.checkIfLeftSectionItemsDisplayedAndHaveProperText(data.getExpectedLeftSectionItemsText());
    }
}
