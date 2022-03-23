package com.epam.tc.hw.jdi.uiobjects.site.custom;

import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.complex.dropdown.DropdownExpand;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.ByText;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import com.epam.tc.hw.jdi.entities.MetalsColorsFormEntry;

public class MetalsColorsForm extends Form<MetalsColorsFormEntry> {

    @JDropdown(root = "div[ui=dropdown]", value = ".filter-option",
               list = "li", expand = ".caret")
    public static DropdownExpand color;

    @JDropdown(root = "div[ui=combobox]", value = "input",
               list = "li", expand = ".caret")
    public static Dropdown metals;

    @JDropdown(root = "#salad-dropdown", value = ".dropdown-menu",
               list = "li", expand = ".caret")
    public static Dropdown vegetables;

    @UI("#elements-checklist > .checkbox")
    public static WebList elements;

    @UI("[name*=custom_radio]")
    public static RadioButtons summary;

    @ByText("Submit")
    public static Button submit;
}
