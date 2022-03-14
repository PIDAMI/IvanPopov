package com.epam.tc.hw.jdi.uiobjects.site.custom;

public enum HeaderMenuData {

    Home("Home"),
    ContactForm("Contact form"),
    Support("Support"),
    Dates("Dates"),
    Service("Service"),
    ComplexTable("Complex Table"),
    SimpleTable("Simple Table"),
    UserTable("User Table"),
    TableWithPages("Table with pages"),
    DifferentElements("Different elements"),
    MetalsColors("Metals & Colors");

    public String value;
    HeaderMenuData(String value) {
        this.value = value;
    }
}
