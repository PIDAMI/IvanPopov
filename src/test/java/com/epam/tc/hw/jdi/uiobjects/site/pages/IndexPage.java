package com.epam.tc.hw.jdi.uiobjects.site.pages;

import com.epam.jdi.light.elements.complex.Menu;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Link;

@Url("/index.html")
public class IndexPage extends WebPage {
    @Css(".uui-header.dark-gray")
    public static Menu headerMenu;
}
