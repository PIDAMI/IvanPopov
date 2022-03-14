package com.epam.tc.hw.jdi.steps;

import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.metalsColorsPage;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.userName;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw.jdi.entities.MetalsColorsFormEntry;
import com.epam.tc.hw.jdi.uiobjects.site.pages.MetalsColorsPage;
import io.qameta.allure.Step;
import java.util.List;

public class AssertStep {
    @Step
    public static void checkUserIsLoggedIn() {
        assertThat(userName.isDisplayed()).isTrue();
    }

    @Step
    public static void checkMetalsColorsPageOpened() {
        assertThat(metalsColorsPage.isOpened()).isTrue();
    }

    @Step
    private static void checkResultSummary(List<String> summary) {
        int summaryTotal = summary.stream()
                                  .map(Integer::parseInt)
                                  .reduce(Integer::sum)
                                  .get();
        checkResultField("Summary: " + summaryTotal);
    }

    @Step
    private static void checkResultElements(List<String> elements) {
        String elementsRepresentaion =  String.join(", ", elements);
        checkResultField("Elements: " + elementsRepresentaion);
    }

    @Step
    private static void checkResultColor(String color) {
        checkResultField("Color: " + color);
    }

    @Step
    private static void checkResultMetal(String metal) {
        checkResultField("Metal: " + metal);
    }

    @Step
    private static void checkResultVegetables(List<String> vegetables) {
        String vegetablesRepresentaion = String.join(", ", vegetables);
        checkResultField("Vegetables: " + vegetablesRepresentaion);
    }

    private static void checkResultField(String expectedFieldValue) {
        assertThat(MetalsColorsPage.results
            .stream()
            .anyMatch(el -> el.getValue().contains(expectedFieldValue))
        ).isTrue();
    }

    @Step
    public static void checkResultHasData(MetalsColorsFormEntry entry) {
        checkResultSummary(entry.summary);
        checkResultElements(entry.elements);
        checkResultColor(entry.color);
        checkResultMetal(entry.metals);
        checkResultVegetables(entry.vegetables);
    }
}
