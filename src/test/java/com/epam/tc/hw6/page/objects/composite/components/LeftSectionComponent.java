package com.epam.tc.hw6.page.objects.composite.components;

import com.epam.tc.hw6.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftSectionComponent extends AbstractBaseComponent {

    @FindBy(css = "ul.sidebar-menu.left > li > a > span")
    List<WebElement> leftSectionElements;

    public LeftSectionComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Getting elements from left section")
    public List<WebElement> getLeftSectionElements() {
        return leftSectionElements;
    }


}
