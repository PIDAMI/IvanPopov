package com.epam.tc.hw3.page.objects.fluents;

import com.epam.tc.hw3.page.objects.base.BaseDifferentElementsPage;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class DifferentElementsFluentPage extends BaseDifferentElementsPage {

    public DifferentElementsFluentPage(WebDriver driver) {
        super(driver);
    }

    public DifferentElementsFluentPage selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        baseSelectElementsCheckboxesAndCheckIfSelected(checkboxesText);
        return this;
    }

    public DifferentElementsFluentPage selectRadioButtonsAndCheckIfSelected(String buttonsText) {
        baseSelectRadioButtonsAndCheckIfSelected(buttonsText);
        return this;
    }

    public DifferentElementsFluentPage selectColorAndCheckIfSelected(String color) {
        baseSelectColorAndCheckIfSelected(color);
        return this;
    }
}
