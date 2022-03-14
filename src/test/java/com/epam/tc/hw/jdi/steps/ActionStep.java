package com.epam.tc.hw.jdi.steps;

import static com.epam.tc.hw.jdi.data.provider.UserDataProvider.USER;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.loginForm;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.logout;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.userIcon;
import static com.epam.tc.hw.jdi.uiobjects.site.custom.MetalsColorsForm.elements;
import static com.epam.tc.hw.jdi.uiobjects.site.custom.MetalsColorsForm.vegetables;
import static com.epam.tc.hw.jdi.uiobjects.site.pages.MetalsColorsPage.calculate;

import com.epam.tc.hw.jdi.entities.MetalsColorsFormEntry;
import com.epam.tc.hw.jdi.uiobjects.site.custom.MetalsColorsForm;
import io.qameta.allure.Step;

public class ActionStep {

    @Step
    public static void fillMetalColorForm(MetalsColorsFormEntry entry) {
        MetalsColorsForm.summary.select(entry.summary.get(0), entry.summary.get(1));
        calculate.click();
        entry.elements.forEach(elements::select);
        MetalsColorsForm.color.select(entry.color);
        MetalsColorsForm.metals.select(entry.metals);
        // Vegetables option is selected by default, unselect it
        vegetables.select("Vegetables");
        entry.vegetables.forEach(vegetables::select);

        MetalsColorsForm.submit.click();
    }

    @Step
    public static void login() {
        userIcon.click();
        loginForm.submit(USER, "enter");
    }

    @Step
    public static void logout() {
        if (!logout.isDisplayed()) {
            userIcon.click();
        }
        logout.click();
    }
}
