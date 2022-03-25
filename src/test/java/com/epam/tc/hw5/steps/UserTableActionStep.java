package com.epam.tc.hw5.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.entities.UserTable;
import com.epam.tc.hw5.entities.UserTableRow;
import com.epam.tc.hw5.page.objects.voids.JdiIndexVoidPage;
import com.epam.tc.hw5.page.objects.voids.UserTableVoidPage;
import com.epam.tc.hw5.steps.AbstractStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserTableActionStep extends AbstractStep {

    private UserTableVoidPage tablePage;

    public UserTableActionStep(JdiIndexVoidPage indexPage, UserTableVoidPage tablePage) {
        super(indexPage);
        this.tablePage = tablePage;
    }

    @When("I click on {string} button in Header")
    public void clickButtonInHeader(String buttonText) {
        indexPage.header().clickServiceButton(buttonText);
    }

    @When("I click on {string} button in Service dropdown")
    public void clickButtonInService(String buttonText) {
        indexPage.header().clickButtonInService(buttonText);
    }

    @When("I select {string} checkbox for {string}")
    public void selectCheckboxForUser(String checkboxName, String userName) {
        tablePage.clickCheckbox(checkboxName, userName);
    }

    @Then("{int} log row has {string} text in log section")
    public void testCheckboxLogDisplayed(int numLogRows, String logText) {
        List<String> checkboxLogs = tablePage.getCheckboxLog(logText);
        assertThat(checkboxLogs.size()).isEqualTo(numLogRows);
    }

    @Then("{string} page should be opened")
    public void checkPageOpened(String pageTitle) {
        assertThat(driver.getTitle()).isEqualTo(pageTitle);
    }

    @Then("{long} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void checkNumberOfDisplayedNumbers(long expectedNumberOfNumbers) {
        checkSizeTable(expectedNumberOfNumbers);
    }

    @Then("{long} Usernames should be displayed on Users Table on User Table Page")
    public void checkNumberOfDisplayedUsernames(long expectedNumberOfUsers) {
        checkSizeTable(expectedNumberOfUsers);
    }

    @Then("{long} Description texts under images should be displayed on Users Table on User Table Page")
    public void checkNumberOfDisplayedDescriptions(long expectedNumberOfDescriptions) {
        checkSizeTable(expectedNumberOfDescriptions);
    }

    @Then("{long} checkboxes should be displayed on Users Table on User Table Page")
    public void checkNumberOfDisplayedCheckboxes(long expectedNumberOfCheckboxes) {
        checkSizeTable(expectedNumberOfCheckboxes);
    }

    private void checkSizeTable(long expectedSize) {
        UserTable table = tablePage.getTable();
        // if table has row without some field, test crushes
        assertThat(table.getRows().size()).isEqualTo(expectedSize);
    }

    // Equal method is redefined for UserTableRow to match
    // even if some field are null
    @Then("User table should contain following values:")
    public void checkUserTableIncludesData(DataTable data) {
        List<Map<String, String>> expectedRows = data.asMaps(String.class, String.class);
        List<UserTableRow> table = tablePage.getTable().getRows();

        for (Map<String, String> expectedRow : expectedRows) {
            Long num = Long.parseLong(expectedRow.get("Number"));
            String name = expectedRow.get("User");
            String description = expectedRow.get("Description");
            assertThat(table.contains(
                new UserTableRow(num, null, description, name, null)
            )).isTrue();
        }
    }

    @Then("droplist should contain values in column Type for user {string}")
    public void checkDroplistValues(String userName, DataTable data) {
        List<String> expectedOptions = data.asList(String.class);
        List<String> actualDroplistOptions = tablePage.getDroplistOptions(userName);

        expectedOptions
            .stream()
            .skip(1) // skip header
            .forEach(option -> assertThat(actualDroplistOptions.contains(option)).isTrue());
    }

}
