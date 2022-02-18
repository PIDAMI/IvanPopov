package com.epam.tc.hw3.ex2;

import static java.util.Map.entry;

import com.epam.tc.hw3.BaseData;
import com.epam.tc.hw3.entities.User;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class SecondExerciseData extends BaseData {

    @DataProvider(name = "second exercise data")
    public static Object[][] provideData() {

        User user = loadUserFromProperties();

        List<String> elementsCheckboxesText = List.of("Water", "Wind");
        String radioCheckboxesText = "Selen";
        String color = "Yellow";

        return new Object[][]{
            {EXPECTED_BROWSER_TITLE, user,
                elementsCheckboxesText,
                radioCheckboxesText, color}
        };

    }

}
