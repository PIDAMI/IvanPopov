package com.epam.tc.hw5.steps;

import com.epam.tc.hw5.context.TestContext;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractStep {

    protected JdiIndexVoidPage indexPage;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractStep(JdiIndexVoidPage indexPage) {
        driver = TestContext.getInstance().getObject("driver", WebDriver.class);
        wait = TestContext.getInstance().getObject("wait", WebDriverWait.class);
        this.indexPage = indexPage;
    }
}
