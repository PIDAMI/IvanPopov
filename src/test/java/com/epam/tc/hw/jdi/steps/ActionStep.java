package com.epam.tc.hw.jdi.steps;

import static com.epam.tc.hw.jdi.data.provider.UserDataProvider.USER;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.loginForm;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.userIcon;

import io.qameta.allure.Step;

public class ActionStep {
    @Step
    public void login() {
        userIcon.click();
        loginForm.submit(USER, "enter");
    }

}
