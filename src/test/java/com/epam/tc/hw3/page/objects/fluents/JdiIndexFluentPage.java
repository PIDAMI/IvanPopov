package com.epam.tc.hw3.page.objects.fluents;

import com.epam.tc.hw3.page.objects.base.BaseJdiIndexPage;
import org.openqa.selenium.WebDriver;

public class JdiIndexFluentPage extends BaseJdiIndexPage {

    public JdiIndexFluentPage(WebDriver driver) {
        super(driver);
    }

    // throws NoSuchElementException if such frame doesn't exist
    public JdiIndexFluentPage switchToFrameWithButton(final String buttonValue) {
        baseSwitchToFrameWithButton(buttonValue);
        return this;
    }

    public JdiIndexFluentPage switchToMainWindow() {
        baseSwitchToMainWindow();
        return this;
    }

    public DifferentElementsFluentPage gotoDifferentElementPage() {
        baseGotoDifferentElementPage();
        return new DifferentElementsFluentPage(driver);
    }
}
