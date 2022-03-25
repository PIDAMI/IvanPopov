package com.epam.tc.hw5.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.context.TestContext;
import com.epam.tc.hw5.entities.User;
import com.epam.tc.hw5.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectableElementsActionStep extends AbstractStep {

    private DifferentElementsVoidPage differentElementsPage;

    public SelectableElementsActionStep(JdiIndexVoidPage indexVoidPage,
                                        DifferentElementsVoidPage differentElementsPage) {
        super(indexVoidPage);
        this.differentElementsPage = differentElementsPage;
    }

    @When("I open JDI GitHub site")
    public void openIndexPage() {
        driver.get(indexPage.getUrl());
    }

    @When("I login as user {string}")
    public void login(String userName) {
        User user = (User) TestContext.getInstance().getObject("nameToUserMap", Map.class).get(userName);
        indexPage.profile().login(user);
    }

    @When("I go to Different Elements page")
    public void gotoDifferentElementPage() {
        indexPage.gotoDifferentElementPage();
    }

    @When("I select elements checkboxes with values {checkboxesText}")
    public void selectElementsCheckboxesAndCheckIfSelected(List<String> elementsCheckboxesText) {
        differentElementsPage
            .selectElementsCheckboxesAndCheckIfSelected(elementsCheckboxesText);
    }

    @When("I select radio button with value {string}")
    public void selectRadioButtonsAndCheckIfSelected(String radioButtonText) {
        differentElementsPage
            .selectRadioButtonAndCheckIfSelected(radioButtonText);
    }

    @When("I select color option with value {string}")
    public void selectColorAndCheckIfSelected(String color) {
        differentElementsPage.selectColorAndCheckIfSelected(color);
    }

    @Then("Elements checkboxes log is displayed and its value corresponds to {checkboxesText}")
    public void checkIfElementCheckboxesLogIsDisplayed(List<String> elementsCheckboxesText) {
        List<String> elementsLog = differentElementsPage
            .getElementsCheckboxLogIfDisplayed(elementsCheckboxesText);
        assertThat(elementsLog.size()).isEqualTo(elementsCheckboxesText.size());
    }

    @Then("Radio buttons log is displayed and its value corresponds to {string}")
    public void checkIfRadioButtonLogIsDisplayed(String radioButtonText) {
        List<String> radioLog = differentElementsPage
            .getRadioButtonsLogIfDisplayed(radioButtonText);
        assertThat(radioLog.isEmpty()).isFalse();
    }

    @Then("Color option log is displayed and its value corresponds to {string}")
    public void checkIfColorOptionLogIsDisplayed(String color) {
        List<String> colorLog = differentElementsPage.getColorLogIfDisplayed(color);
        assertThat(colorLog.isEmpty()).isFalse();
    }

    @ParameterType(value = "(.*)")
    public List<String> checkboxesText(String s) {
        return Arrays.stream(s.split(","))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }
}
