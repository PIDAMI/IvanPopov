package com.epam.tc.hw6.page.objects.voids;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractBasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractBasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
