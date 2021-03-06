package com.epam.tc.hw3.page.objects.composite.components;

import com.epam.tc.hw3.page.objects.composite.AbstractBaseComponent;
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

    public List<WebElement> getLeftSectionElements() {
        return leftSectionElements;
    }
}
