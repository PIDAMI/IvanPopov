package com.epam.tc.hw3.page.objects.composite.components;

import com.epam.tc.hw3.page.objects.composite.AbstractBaseComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractBaseComponent {

    @FindBy(css = ".uui-navigation.nav.navbar-nav > *")
    private List<WebElement> navigationButtons;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }


    public List<WebElement> getNavigationButtons() {
        return List.copyOf(navigationButtons);
    }

    public List<String> getNavigationButtonsText() {
        return navigationButtons.stream()
                                .map(WebElement::getText)
                                .collect(Collectors.toList());
    }
}
