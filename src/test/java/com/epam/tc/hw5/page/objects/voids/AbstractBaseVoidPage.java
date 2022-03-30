package com.epam.tc.hw5.page.objects.voids;

import com.epam.tc.hw5.context.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBaseVoidPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractBaseVoidPage() {
        this.driver = TestContext.getInstance().getObject("driver", WebDriver.class);
        this.wait = TestContext.getInstance().getObject("wait", WebDriverWait.class);
    }
}
