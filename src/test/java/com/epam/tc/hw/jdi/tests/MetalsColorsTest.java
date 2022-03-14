package com.epam.tc.hw.jdi.tests;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.indexPage;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.metalsColorsPage;
import static com.epam.tc.hw.jdi.uiobjects.site.pages.IndexPage.headerMenu;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw.jdi.steps.ActionStep;
import com.epam.tc.hw.jdi.steps.AssertStep;
import com.epam.tc.hw.jdi.uiobjects.site.JdiSite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MetalsColorsTest {

    @BeforeSuite
    public void beforeSuite() {
        initSite(JdiSite.class);
    }

    @AfterSuite
    public void afterSuite() {
        killAllSeleniumDrivers();
    }

    @BeforeMethod
    public void setup() {
        indexPage.open();
    }

    @AfterMethod
    public void tearDown() {

    }

    @Test
    public void testMetalsColorsForm() {
        ActionStep actionStep = new ActionStep();
        AssertStep assertStep = new AssertStep();

        actionStep.login();
        assertStep.checkUserIsLoggedIn();
        headerMenu.select("Metals & Colors");
        assertThat(metalsColorsPage.isOpened()).isTrue();
    }

}
