package com.epam.tc.hw5.page.objects.composite.components;

import com.epam.tc.hw5.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractBaseComponent {

    @FindBy(xpath = "//div[@class='uui-header dark-gray']//li")
    private List<WebElement> navigationButtons;

    @FindBy(xpath = "//div[@class='uui-header dark-gray']//ul[@class='dropdown-menu']/li")
    private List<WebElement> serviceButtons;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Getting navigation buttons' text")
    public void clickServiceButton(String buttonText) {
        List<WebElement> buttons = navigationButtons
            .stream()
            .filter(el -> {
                WebElement childElementWithText = el.findElement(By.xpath("./a"));
                return childElementWithText.getText().trim().equalsIgnoreCase(buttonText);
            })
            .collect(Collectors.toList());

        if (buttons.size() > 0) {
            buttons.get(0).click();
        }
    }

    public void clickButtonInService(String buttonText) {
        List<WebElement> buttons = serviceButtons
            .stream()
            .filter(el -> el.getText().trim().equalsIgnoreCase(buttonText))
            .collect(Collectors.toList());

        if (buttons.size() > 0) {
            buttons.get(0).click();
        }
    }

}
