package com.epam.tc.hw2.ex2;

import static java.util.Map.entry;

import com.epam.tc.hw2.BaseData;
import com.epam.tc.hw2.entities.User;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class SecondExerciseData extends BaseData {

    @DataProvider(name = "second exercise data")
    public static Object[][] provideData() {

        User user = loadUserFromProperties();

        Map<String, String> checkboxElementsTextToLogFormat = Map.ofEntries(
            entry("Water", "Water: condition changed to true"),
            entry("Wind", "Wind: condition changed to true")
        );

        Map<String, String> checkboxRadioTextToLogFormat = Map.ofEntries(
            entry("Selen", "Selen")
        );

        Map<String, String> colorTextToLogFormat = Map.ofEntries(
            entry("Yellow", "Colors: value changed to Yellow")
        );

        return new Object[][]{
            {INDEX_PAGE_URL, EXPECTED_BROWSER_TITLE, user, checkboxElementsTextToLogFormat,
                checkboxRadioTextToLogFormat, colorTextToLogFormat}
        };

    }

}
