package com.epam.tc.hw3.page.objects.voids;

import com.epam.tc.hw3.page.objects.base.BaseJdiIndexPage;
import org.openqa.selenium.WebDriver;

public class JdiIndexVoidPage extends BaseJdiIndexPage {

    public JdiIndexVoidPage(WebDriver driver) {
        super(driver);
    }

    public void switchToFrameWithButton(final String buttonValue) {
        baseSwitchToFrameWithButton(buttonValue);
    }

    public void switchToMainWindow() {
        baseSwitchToMainWindow();
    }

    public void gotoDifferentElementPage() {
        baseGotoDifferentElementPage();
    }
}
