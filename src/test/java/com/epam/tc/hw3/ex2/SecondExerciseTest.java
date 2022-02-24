package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.AbstractBaseTest;
import com.epam.tc.hw3.entities.User;
import com.epam.tc.hw3.page.objects.fluents.DifferentElementsFluentPage;
import com.epam.tc.hw3.page.objects.fluents.JdiIndexFluentPage;
import com.epam.tc.hw3.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw3.page.objects.voids.JdiIndexVoidPage;
import java.util.List;
import org.testng.annotations.Test;

public class SecondExerciseTest extends AbstractBaseTest {

    @Test()
    public void secondExerciseWithVoidPagesTest() {
        SecondExerciseData data = new SecondExerciseData();
        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(data.getExpectedBrowserTitle());

        // 3. Perform login
        User user = data.getUser();
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        String userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName).isEqualTo(user.getDisplayedName());

        // 5. Select checkboxes
        indexPage.gotoDifferentElementPage();
        DifferentElementsVoidPage differentElementsPage = new DifferentElementsVoidPage(driver);

        List<String> elementsCheckboxesText = data.getElementsCheckboxesText();
        differentElementsPage.selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);

        // 7. Select radio
        String radioCheckboxesText = data.getRadioCheckboxesText();
        differentElementsPage.selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);

        // 8. Select in dropdown
        String color = data.getColor();
        differentElementsPage.selectColorAndCheckIfSelected(color);

        // 9. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());

        // 9. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        List<String> radioLog = differentElementsPage
            .getRadioCheckboxLogIfDisplayed(radioCheckboxesText);
        assertThat(radioLog.isEmpty()).isFalse();

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        List<String> colorLog = differentElementsPage
            .getColorLogIfDisplayed(color);
        assertThat(colorLog.isEmpty()).isFalse();
    }

    @Test()
    public void secondExerciseWithFluentPagesTest() {
        SecondExerciseData data = new SecondExerciseData();
        JdiIndexFluentPage indexPage = new JdiIndexFluentPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(data.getExpectedBrowserTitle());

        // 3. Perform login
        User user = data.getUser();
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        String userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName).isEqualTo(user.getDisplayedName());

        // 5. Select checkboxes
        DifferentElementsFluentPage differentElementsPage = indexPage.gotoDifferentElementPage();
        List<String> elementsCheckboxesText = data.getElementsCheckboxesText();
        differentElementsPage.selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);

        // 7. Select radio
        String radioCheckboxesText = data.getRadioCheckboxesText();
        differentElementsPage.selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);

        // 8. Select in dropdown
        String color = data.getColor();
        differentElementsPage.selectColorAndCheckIfSelected(color);

        // 9. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());

        // 9. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        List<String> radioLog = differentElementsPage
            .getRadioCheckboxLogIfDisplayed(radioCheckboxesText);
        assertThat(radioLog.isEmpty()).isFalse();

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        List<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isEmpty()).isFalse();
    }
}
