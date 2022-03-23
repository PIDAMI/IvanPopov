package com.epam.tc.hw.jdi.uiobjects.site;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.tc.hw.jdi.uiobjects.site.custom.LoginForm;
import com.epam.tc.hw.jdi.uiobjects.site.pages.IndexPage;
import com.epam.tc.hw.jdi.uiobjects.site.pages.MetalsColorsPage;
import org.openqa.selenium.WebElement;

@JSite("https://jdi-testing.github.io/jdi-light/")
public class JdiSite {

    public static IndexPage indexPage;
    public static MetalsColorsPage metalsColorsPage;

    @UI("//form[@id='login-form']")
    public static LoginForm loginForm;

    @Css(".profile-photo [ui=label]")
    public static UIElement userName;

    @Css("img#user-icon")
    public static UIElement userIcon;

    @Css(".logout")
    public static WebElement logout;

}
