package com.epam.tc.hw.jdi.uiobjects.site.pages;

import com.epam.jdi.light.elements.complex.Menu;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;

@Url("/index.html")
public class IndexPage extends WebPage {

    @UI(".uui-navigation.nav.navbar-nav.m-l8 li")
    public static Menu headerMenu;
}
