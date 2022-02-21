package com.epam.tc.hw4.page.objects.composite.components;

import com.epam.tc.hw4.page.objects.composite.AbstractBaseComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BottomPartComponent extends AbstractBaseComponent {


    @FindBy(css = "div.row.clerafix.benefits > div.col-sm-3 > "
        + "div.benefit > div.benefit-icon > span.icons-benefit")
    private List<WebElement> images;

    @FindBy(css = "div.row.clerafix.benefits > div.col-sm-3 > "
        + "div.benefit > span.benefit-txt")
    private List<WebElement> textAfterImages;

    public BottomPartComponent(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getImages() {
        return List.copyOf(images);
    }

    public List<String> getTextAfterImage() {
        return textAfterImages.stream()
                              .map(WebElement::getText)
                              .collect(Collectors.toList());
    }

}
