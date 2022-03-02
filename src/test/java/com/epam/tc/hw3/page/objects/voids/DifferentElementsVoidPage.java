package com.epam.tc.hw3.page.objects.voids;

import com.epam.tc.hw3.page.objects.base.BaseDifferentElementsPage;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class DifferentElementsVoidPage extends BaseDifferentElementsPage {

    public DifferentElementsVoidPage(WebDriver driver) {
        super(driver);
    }

    public void selectElementsCheckboxesAndCheckIfSelected(List<String> checkboxesText) {
        baseSelectElementsCheckboxesAndCheckIfSelected(checkboxesText);
    }

    public void selectRadioButtonsAndCheckIfSelected(final String buttonsText) {
        baseSelectRadioButtonsAndCheckIfSelected(buttonsText);
    }

    public void selectColorAndCheckIfSelected(final String color) {
        baseSelectColorAndCheckIfSelected(color);
    }

}
