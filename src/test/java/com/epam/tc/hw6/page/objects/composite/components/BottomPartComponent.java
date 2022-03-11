package com.epam.tc.hw6.page.objects.composite.components;

import com.epam.tc.hw6.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BottomPartComponent extends AbstractBaseComponent {


    @FindBy(css = "div.row.clerafix.benefits > div.col-sm-3 > "
        + "div.benefit > div.benefit-icon > span.icons-benefit")
    private List<WebElement> images;

    @FindBy(css = "div.row.clerafix.benefits > div.col-sm-3 > "
        + "div.benefit > span.benefit-txt")
    private List<WebElement> textAfterImages;

    public BottomPartComponent(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    @Step("Getting images in bottom part")
    public List<WebElement> getImages() {
        return List.copyOf(images);
    }

    @Step("Getting text after images in bottom part")
    public List<String> getTextAfterImage() {
        return textAfterImages.stream()
                              .map(WebElement::getText)
                              .collect(Collectors.toList());
    }

}
