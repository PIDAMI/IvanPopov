package com.epam.tc.hw4.page.objects.composite.components;

import com.epam.tc.hw4.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftSectionComponent extends AbstractBaseComponent {

    @FindBy(css = "ul.sidebar-menu.left > li > a > span")
    List<WebElement> leftSectionElements;

    public LeftSectionComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Getting elements from left section")
    public List<WebElement> getLeftSectionElements() {
        return leftSectionElements;
    }


}
