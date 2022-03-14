package com.epam.tc.hw.jdi.uiobjects.site.custom;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.tc.hw.jdi.entities.User;
import java.awt.TextField;

public class LoginForm extends Form<User> {

    @UI("//input[@id='name']")
    public TextField name;
    @UI("//input[@id='password']")
    public TextField password;
    @UI("#login-button")
    public Button submit;
}
