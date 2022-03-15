package com.epam.tc.hw.jdi.tests;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.tc.hw.jdi.steps.ActionStep.fillMetalColorForm;
import static com.epam.tc.hw.jdi.steps.AssertStep.checkResultHasData;
import static com.epam.tc.hw.jdi.uiobjects.site.JdiSite.indexPage;
import static com.epam.tc.hw.jdi.uiobjects.site.pages.IndexPage.headerMenu;

import com.epam.tc.hw.jdi.data.provider.FormDataProvider;
import com.epam.tc.hw.jdi.entities.MetalsColorsFormEntry;
import com.epam.tc.hw.jdi.steps.ActionStep;
import com.epam.tc.hw.jdi.steps.AssertStep;
import com.epam.tc.hw.jdi.uiobjects.site.JdiSite;
import com.epam.tc.hw.jdi.uiobjects.site.custom.HeaderMenuData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MetalsColorsTest {

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() {
        initSite(JdiSite.class);
    }

    @AfterClass(alwaysRun = true)
    public void afterSuite() {
        killAllSeleniumDrivers();
    }

    @BeforeMethod
    public void setup() {
        indexPage.open();
    }

    @AfterMethod
    static void logout() {
        ActionStep.logout();
    }

    @Test(dataProvider = "metalsColorsFormData",
          dataProviderClass = FormDataProvider.class)
    public void testMetalsColorsForm(MetalsColorsFormEntry entry) {
        ActionStep.login();
        AssertStep.checkUserIsLoggedIn();
        headerMenu.select(HeaderMenuData.MetalsColors);
        AssertStep.checkMetalsColorsPageOpened();
        fillMetalColorForm(entry);
        checkResultHasData(entry);
    }
}
