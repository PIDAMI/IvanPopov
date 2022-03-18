package com.epam.tc.hw4.ex2;

import com.epam.tc.hw4.AbstractBaseTest;
import com.epam.tc.hw4.entities.User;
import com.epam.tc.hw4.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw4.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Homework 4")
@Story("Exercise 2 test")
public class SecondExerciseTest extends AbstractBaseTest {

    @Test()
    public void secondExerciseWithVoidPagesTest() {
        SecondExerciseData data = new SecondExerciseData();
        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        DifferentElementsVoidPage differentElementsPage = new DifferentElementsVoidPage(driver);
        SecondExerciseUtil util = new SecondExerciseUtil(driver, wait, indexPage, differentElementsPage);
        // 2. Assert Browser title
        util.checkIfTitleCorrect(data.getExpectedBrowserTitle());

        // 3. Perform login
        User user = data.getUser();
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        util.checkIfUserLoggedIn(user);

        // 5. Select checkboxes
        indexPage.gotoDifferentElementPage();
        util.selectElementsCheckboxesAndCheckIfSelected(data.getElementsCheckboxesText());

        // 7. Select radio
        util.selectRadioCheckboxesAndCheckIfSelected(data.getRadioCheckboxesText());

        // 8. Select in dropdown
        util.selectColorAndCheckIfSelected(data.getColor());

        // 9. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        util.checkIfElementCheckboxesLogIsDisplayed(data.getElementsCheckboxesText());

        // 9. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        util.checkIfRadioCheckboxesLogIsDisplayed(data.getRadioCheckboxesText());

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        util.checkIfColorOptionsLogIsDisplayed(data.getColor());
    }
}
