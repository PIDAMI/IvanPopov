package com.epam.tc.hw.jdi.steps;

import static com.epam.tc.hw.jdi.data.provider.UserDataProvider.USER;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.userName;
import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;

public class AssertStep {
    @Step
    public void checkUserIsLoggedIn() {
        assertThat(userName.isDisplayed()).isTrue();
//        assertThat(userName.getText()).isEqualTo(USER.name);
    }
}
