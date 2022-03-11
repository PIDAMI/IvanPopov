package com.epam.tc.hw6.page.objects.composite.components;

import com.epam.tc.hw6.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderComponent extends AbstractBaseComponent {

    @FindBy(css = "nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li")
    private List<WebElement> navigationButtons;

    public HeaderComponent(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
    }

    @Step("Getting navigation buttons")
    public List<WebElement> getNavigationButtons() {
        return List.copyOf(navigationButtons);
    }

    @Step("Getting navigation buttons' text")
    public List<String> getNavigationButtonsText() {
        return navigationButtons.stream()
                                .map(WebElement::getText)
                                .collect(Collectors.toList());
    }
}
