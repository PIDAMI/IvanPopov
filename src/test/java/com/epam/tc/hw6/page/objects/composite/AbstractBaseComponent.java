package com.epam.tc.hw6.page.objects.composite;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected AbstractBaseComponent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

}
