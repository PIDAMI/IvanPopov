package com.epam.tc.hw5.ex1;

import com.epam.tc.hw5.AbstractBaseTest;
import com.epam.tc.hw5.entities.User;
import com.epam.tc.hw5.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Homework 5")
public class FirstExerciseTest extends AbstractBaseTest {

    @Test()
    public void testSelectablesDifferentElementsPage() {
        FirstExerciseData data = new FirstExerciseData();
        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        DifferentElementsVoidPage differentElementsPage = new DifferentElementsVoidPage(driver);
        FirstExerciseUtil util = new FirstExerciseUtil(driver, wait, indexPage, differentElementsPage);
        // 2. Perform login
        User user = data.getUser();
        indexPage.profile().login(user);
        // 3. Select checkboxes
        indexPage.gotoDifferentElementPage();
        // 4. Select checkboxes
        util.selectElementsCheckboxesAndCheckIfSelected(data.getElementsCheckboxesText());
        // 5. Select radio
        util.selectRadioCheckboxesAndCheckIfSelected(data.getRadioCheckboxesText());
        // 6. Select in dropdown
        util.selectColorAndCheckIfSelected(data.getColor());
        // 7. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        util.checkIfElementCheckboxesLogIsDisplayed(data.getElementsCheckboxesText());
        // 7. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        util.checkIfRadioCheckboxesLogIsDisplayed(data.getRadioCheckboxesText());
        // 7. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        util.checkIfColorOptionsLogIsDisplayed(data.getColor());
    }
}
