package com.epam.tc.hw5.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.AbstractBaseUtil;
import com.epam.tc.hw5.page.objects.voids.DifferentElementsVoidPage;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Step;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstExerciseUtil extends AbstractBaseUtil {

    private DifferentElementsVoidPage differentElementsPage;

    public FirstExerciseUtil(WebDriver driver, WebDriverWait wait, JdiIndexVoidPage indexPage,
                             DifferentElementsVoidPage differentElementsPage) {
        super(driver, wait, indexPage);
        this.differentElementsPage = differentElementsPage;
    }

//    @Step("Select elements checkboxes with values {elementsCheckboxesText} "
//        + "and check if they've been selected")
//    public List<WebElement> selectElementsCheckboxesAndGetSelected(List<String> elementsCheckboxesText) {
//        return differentElementsPage
//            .selectElementsCheckboxesAndGetSelected(elementsCheckboxesText);
//    }
//
//    @Step("Select radio checkboxes with values {radioCheckboxesText} "
//        + "and check if it's been selected")
//    public List<WebElement> selectRadioCheckboxesAndGetSelected(String radioCheckboxesText) {
//        return differentElementsPage
//            .selectRadioCheckboxesAndGetSelected(radioCheckboxesText);
//    }
//
//    @Step("Select color option with value {color} and check if it's been selected")
//    public WebElement selectColorAndGetSelected(String color) {
//        return differentElementsPage.selectColorAndGetSelected(color);
//    }




}
