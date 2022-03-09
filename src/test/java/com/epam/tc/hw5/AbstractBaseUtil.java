package com.epam.tc.hw5;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.entities.User;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Step;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBaseUtil {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JdiIndexVoidPage indexPage;

    public AbstractBaseUtil(WebDriver driver, WebDriverWait wait,
                            JdiIndexVoidPage indexPage) {
        this.driver = driver;
        this.wait = wait;
        this.indexPage = indexPage;
    }

    @Step("Check if page title is equal to {expectedTitle}")
    public void checkIfTitleCorrect(String expectedTitle) {
        assertThat(indexPage.getPageTitle()).isEqualTo(expectedTitle);
    }

}
