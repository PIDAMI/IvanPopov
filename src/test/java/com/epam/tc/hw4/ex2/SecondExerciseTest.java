package com.epam.tc.hw4.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw4.AbstractBaseTest;
import com.epam.tc.hw4.entities.User;
import com.epam.tc.hw4.page.objects.fluents.DifferentElementsFluentPage;
import com.epam.tc.hw4.page.objects.fluents.JdiIndexFluentPage;
import com.epam.tc.hw4.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw4.page.objects.voids.JdiIndexVoidPage;
import java.util.List;
import java.util.Optional;
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
        Optional<String> userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isTrue();
        assertThat(userName.get()).isEqualTo(user.getDisplayedName());

        // 5. Select checkboxes
        indexPage.gotoDifferentElementPage();
        DifferentElementsVoidPage differentElementsPage = new DifferentElementsVoidPage(driver);

        List<String> elementsCheckboxesText = data.getElementsCheckboxesText();
        List<Boolean> checkboxesChecked = differentElementsPage
            .selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);
        checkboxesChecked.forEach(checked -> assertThat(checked).isTrue());

        // 7. Select radio
        String radioCheckboxesText = data.getRadioCheckboxesText();
        Boolean radioChecked = differentElementsPage
            .selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);
        assertThat(radioChecked).isTrue();

        // 8. Select in dropdown
        String color = data.getColor();
        Boolean colorChecked = differentElementsPage
            .selectColorAndCheckIfSelected(color);
        assertThat(colorChecked).isTrue();

        // 9. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());

        // 9. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        Optional<String> radioLog = differentElementsPage
            .getRadioCheckboxLogIfDisplayed(radioCheckboxesText);
        assertThat(radioLog.isPresent()).isTrue();

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        Optional<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isPresent()).isTrue();
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
        Optional<String> userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isTrue();
        assertThat(userName.get()).isEqualTo(user.getDisplayedName());

        // 5. Select checkboxes
        DifferentElementsFluentPage differentElementsPage = indexPage.gotoDifferentElementPage();
        List<String> elementsCheckboxesText = data.getElementsCheckboxesText();
        List<Boolean> checkboxesChecked = differentElementsPage
            .selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);
        checkboxesChecked.forEach(checked -> assertThat(checked).isTrue());

        // 7. Select radio
        String radioCheckboxesText = data.getRadioCheckboxesText();
        Boolean radioChecked = differentElementsPage
            .selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);
        assertThat(radioChecked).isTrue();

        // 8. Select in dropdown
        String color = data.getColor();
        Boolean colorChecked = differentElementsPage
            .selectColorAndCheckIfSelected(color);
        assertThat(colorChecked).isTrue();

        // 9. Assert that
        // for each checkbox there is an individual log row
        // and value is corresponded to the status of checkbox
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());

        // 9. Assert that
        // for radio button there is a log row
        // and value is corresponded to the status of radio button
        Optional<String> radioLog = differentElementsPage
            .getRadioCheckboxLogIfDisplayed(radioCheckboxesText);
        assertThat(radioLog.isPresent()).isTrue();

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        Optional<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isPresent()).isTrue();
    }
}
