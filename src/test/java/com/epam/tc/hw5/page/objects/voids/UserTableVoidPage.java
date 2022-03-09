package com.epam.tc.hw5.page.objects.voids;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.entities.UserTable;
import com.epam.tc.hw5.entities.UserTableRow;
import com.epam.tc.hw5.entities.UserTableRow.Role;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserTableVoidPage extends AbstractBaseVoidPage {

    @FindBy(xpath = "//table[@id='user-table']/tbody/tr")
    private List<WebElement> tableRows;

    private UserTable table;

    public UserTableVoidPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public UserTable getTable() {
        if (Objects.isNull(table)) {
            table = transformToTable(tableRows);
        }
        return table;
    }

    public UserTable transformToTable(List<WebElement> rows) {
        List<UserTableRow> userTableRowsrows = tableRows
            .stream()
            .map(el -> {
                String description = getDescription(el);
                long number = getNumber(el);
                String userName = getUsername(el);
                String selectedValue = getSelectedValue(el);
                boolean isCheckboxChecked = getIsCheckboxChecked(el);

                return new UserTableRow(number, Role.valueOf(selectedValue.toUpperCase(Locale.ROOT)),
                    description, userName, isCheckboxChecked);
            }).collect(Collectors.toList());

        return new UserTable(userTableRowsrows);
    }


    public boolean getIsCheckboxChecked(WebElement rowRootElement) {
        WebElement checkbox = rowRootElement.findElement(By.xpath("./td//input[@type='checkbox']"));
        if (!checkbox.isDisplayed()) {
            throw new IllegalArgumentException("checkbox is not displayed");
        }
        return checkbox.isSelected();
    }

    public String getTextField(WebElement rowRootElement, String xpath) {
        WebElement element = rowRootElement.findElement(By.xpath(xpath));
        if (!element.isDisplayed()) {
            throw new IllegalArgumentException("text field with value " + element.getText() + " is not displayed");
        }
        return element.getText();
    }

    public String getDescription(WebElement rowRootElement) {
        return getTextField(rowRootElement, "./td/div[@class='user-descr']/span");
    }

    public long getNumber(WebElement rowRootElement) {
        return Long.parseLong(getTextField(rowRootElement, "./td[1]"));
    }

    public String getUsername(WebElement rowRootElement) {
        return getTextField(rowRootElement, "./td/a[@href]");
    }


    public String getSelectedValue(WebElement rowRootElement) {
        WebElement selectedOption = rowRootElement.findElement(By.xpath("./td/select"));
        if (!selectedOption.isDisplayed()) {
            throw new IllegalArgumentException("selected options not displayed");
        }
        return new Select(selectedOption).getFirstSelectedOption().getText();
    }

}
