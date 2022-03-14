package com.epam.tc.hw.jdi.uiobjects.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.ByText;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Text;
import com.epam.tc.hw.jdi.uiobjects.site.custom.MetalsColorsForm;
import java.util.List;

@Url("/metals-colors.html")
public class MetalsColorsPage extends WebPage {

    @UI("form.form")
    public static MetalsColorsForm form;

    @UI(".panel-body-list.results > li")
    public static List<Text> results;

    @ByText("Calculate")
    public static Button calculate;
}
