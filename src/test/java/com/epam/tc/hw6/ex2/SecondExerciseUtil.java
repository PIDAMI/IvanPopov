package com.epam.tc.hw6.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw6.AbstractBaseUtil;
import com.epam.tc.hw6.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw6.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Step;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondExerciseUtil extends AbstractBaseUtil {

    private DifferentElementsVoidPage differentElementsPage;

    public SecondExerciseUtil(WebDriver driver, WebDriverWait wait, JdiIndexVoidPage indexPage,
                              DifferentElementsVoidPage differentElementsPage) {
        super(driver, wait, indexPage);
        this.differentElementsPage = differentElementsPage;
    }

    @Step("Select elements checkboxes with values {elementsCheckboxesText} "
        + "and check if they've been selected")
    public void selectElementsCheckboxesAndCheckIfSelected(List<String> elementsCheckboxesText) {
        List<Boolean> checkboxesChecked = differentElementsPage
            .selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);
        checkboxesChecked.forEach(checked -> assertThat(checked).isTrue());
    }

    @Step("Select radio checkboxes with values {radioCheckboxesText} "
        + "and check if it's been selected")
    public void selectRadioCheckboxesAndCheckIfSelected(String radioCheckboxesText) {
        Boolean radioChecked = differentElementsPage
            .selectRadioCheckboxesAndCheckIfSelected(radioCheckboxesText);
        assertThat(radioChecked).isTrue();
    }

    @Step("Select color option with value {color} and check if it's been selected")
    public void selectColorAndCheckIfSelected(String color) {
        Boolean colorChecked = differentElementsPage.selectColorAndCheckIfSelected(color);
        assertThat(colorChecked).isTrue();
    }

    @Step("Check if elements checkboxes log is displayed and its value corresponds to checked "
        + "options {elementsCheckboxesText}")
    public void checkIfElementCheckboxesLogIsDisplayed(List<String> elementsCheckboxesText) {
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());
    }

    @Step("Check if radio checkboxes log is displayed and its value corresponds to checked "
        + "options {radioCheckboxesText}")
    public void checkIfRadioCheckboxesLogIsDisplayed(String radioCheckboxesText) {
        Optional<String> radioLog = differentElementsPage
            .getRadioCheckboxLogIfDisplayed(radioCheckboxesText);
        assertThat(radioLog.isPresent()).isTrue();
    }

    @Step("Check if color options log is displayed and its value corresponds to checked "
        + "options {elementsCheckboxesText}")
    public void checkIfColorOptionsLogIsDisplayed(String color) {
        Optional<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isPresent()).isTrue();
    }


}
