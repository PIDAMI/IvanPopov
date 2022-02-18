package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.AbstractBaseTest;
import com.epam.tc.hw3.page.objects.voids.DifferentElementsPage;
import com.epam.tc.hw3.page.objects.voids.JdiIndexPage;
import java.util.List;
import java.util.Optional;
import org.testng.annotations.Test;

public class SecondExerciseTest extends AbstractBaseTest {

    private final List<String> elementsCheckboxesText = List.of("Water", "Wind");
    private final String radioCheckboxesText = "Selen";
    private final String color = "Yellow";

    @Test
    public void secondExerciseTest() {
        JdiIndexPage indexPage = new JdiIndexPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(siteTitle);

        // 3. Perform login
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        Optional<String> userName = indexPage.profile()
                                             .getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isEqualTo(true);
        assertThat(userName.get()).isEqualTo(expectedUserName);

        // 5. Select checkboxes
        indexPage.gotoDifferentElementPage();
        DifferentElementsPage differentElementsPage =
            new DifferentElementsPage(driver);

        List<Boolean> checkboxesChecked = differentElementsPage
            .selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);
        checkboxesChecked.forEach(checked -> assertThat(checked).isEqualTo(true));

        // 7. Select radio
        Boolean radioChecked = differentElementsPage
            .selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);
        assertThat(radioChecked).isEqualTo(true);

        // 8. Select in dropdown
        Boolean colorChecked = differentElementsPage
            .selectColorAndCheckIfSelected(color);
        assertThat(colorChecked).isEqualTo(true);

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
        assertThat(radioLog.isPresent()).isEqualTo(true);

        // 9. Assert that
        // for dropdown there is a log row
        // and value is corresponded to the selected value.
        Optional<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isPresent()).isEqualTo(true);
    }
}
