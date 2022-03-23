package com.epam.tc.hw3.page.objects.composite;

import static com.epam.tc.hw3.AbstractBaseTest.TIMEOUT_SECONDS;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected AbstractBaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(this.driver, this);
    }
}
