package com.epam.tc.hw5.page.objects.voids;

import com.epam.tc.hw5.entities.UserTable;
import com.epam.tc.hw5.entities.UserTableRow;
import com.epam.tc.hw5.entities.UserTableRow.Role;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UserTableVoidPage extends AbstractBaseVoidPage {

    @FindBy(xpath = "//table[@id='user-table']/tbody/tr")
    private List<WebElement> tableRows;

    private final String checkboxTextXpathFromRowElement = ".//label[contains(text(), '%s')]";
    private final String userNameXpathFromRowElementByString = "./td/a[contains(text(), '%s')]";
    private final String logXpathFromRowElement = "//*[contains(text(), '%s')]";
    private final String checkboxInputElementXpath = ".//input[@type='checkbox']";
    private final String checkboxXpathFromRowElement = "./td//input[@type='checkbox']";
    private final String descriptionXpathFromRowElement = "./td/div[@class='user-descr']/span";
    private final String indexXpathFromRowElement = "./td[1]";
    private final String userNameXpathFromRowElement = "./td/a[@href]";
    private final String selectOptionsXpathFromRowElement = "./td/select";
    private final String checkboxRootElementXpath = ".//div[@class='user-descr']";

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

    public List<String> getCheckboxLog(String logText) {
        return driver.findElements(By.xpath(String.format(
                         logXpathFromRowElement, logText)
                     ))
                     .stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }

    public List<String> getDroplistOptions(String userName) {
        List<WebElement> options = getRowByUserName(userName)
            .stream()
            .map(el -> el.findElements(By.xpath(".//option")))
            .collect(Collectors.toList())
            .get(0);

        return options.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickCheckbox(String checkboxName, String userName) {
        getRowByUserName(userName)
            .get(0)
            .findElements(By.xpath(checkboxRootElementXpath))
            .stream().filter(el -> {
                List<WebElement> checkboxText = el.findElements(By.xpath(String.format(
                    checkboxTextXpathFromRowElement, checkboxName)
                ));
                return !checkboxText.isEmpty();
            })
            .forEach(el -> el.findElement(By.xpath(checkboxInputElementXpath)).click());
    }

    private List<WebElement> getRowByUserName(String userName) {
        return tableRows
            .stream()
            .filter(el -> {
                List<WebElement> userNameNode = el.findElements(By.xpath(
                    String.format(userNameXpathFromRowElementByString, userName)
                ));

                return !userNameNode.isEmpty();
            })
            .collect(Collectors.toList());
    }

    public boolean getIsCheckboxChecked(WebElement rowRootElement) {
        WebElement checkbox = rowRootElement.findElement(By.xpath(checkboxXpathFromRowElement));
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

        // to remove break tags
        return element.getText().replaceAll("\n", " ");
    }

    public String getDescription(WebElement rowRootElement) {
        return getTextField(rowRootElement, descriptionXpathFromRowElement);
    }

    public long getNumber(WebElement rowRootElement) {
        return Long.parseLong(getTextField(rowRootElement, indexXpathFromRowElement));
    }

    public String getUsername(WebElement rowRootElement) {
        return getTextField(rowRootElement, userNameXpathFromRowElement);
    }


    public String getSelectedValue(WebElement rowRootElement) {
        WebElement selectedOption = rowRootElement.findElement(By.xpath(selectOptionsXpathFromRowElement));
        if (!selectedOption.isDisplayed()) {
            throw new IllegalArgumentException("selected options not displayed");
        }

        return new Select(selectedOption).getFirstSelectedOption().getText();
    }

}
