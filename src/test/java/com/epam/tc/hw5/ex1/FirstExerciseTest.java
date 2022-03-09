//package com.epam.tc.hw5.ex1;
//
//import com.epam.tc.hw5.AbstractBaseTest;
//import com.epam.tc.hw5.entities.User;
//import com.epam.tc.hw5.steps.ex1.FirstExerciseActionStep;
//import com.epam.tc.hw5.steps.ex1.FirstExerciseAssertStep;
//import io.qameta.allure.Feature;
//import java.util.List;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.Test;
//
//@Feature("Selectable elements on Different Elements page")
//public class FirstExerciseTest extends AbstractBaseTest {
//
//    @Test()
//    public void testSelectablesDifferentElementsPage() {
//        FirstExerciseData data = new FirstExerciseData();
//        FirstExerciseActionStep actionStep = new FirstExerciseActionStep();
//        FirstExerciseAssertStep assertStep = new FirstExerciseAssertStep();
//        // 2. Perform login
//        User user = data.getUser();
////        actionStep.login(user);
//        // 3. Select checkboxes
//        actionStep.gotoDifferentElementPage();
//        // 4. Select checkboxes
//        List<WebElement> selectedCheckboxes = actionStep.selectElementsCheckboxesAndGetSelected(
//            data.getElementsCheckboxesText()
//        );
//        assertStep.checkElementsCheckboxesSelected(selectedCheckboxes);
//        // 5. Select radio
//        WebElement selectedRadio = actionStep.selectRadioButtonsAndGetSelected(
//            data.getRadioCheckboxesText()
//        );
//        assertStep.checkRadioButtonSelected(selectedRadio);
//        // 6. Select in dropdown
//        WebElement selectedColor = actionStep.selectColorAndGetSelected(data.getColor());
//        assertStep.checkColorOptionSelected(selectedColor, data.getColor());
//        // 7. Assert that
//        // for each checkbox there is an individual log row
//        // and value is corresponded to the status of checkbox
//        assertStep.checkIfElementCheckboxesLogIsDisplayed(data.getElementsCheckboxesText());
//        // 7. Assert that
//        // for radio button there is a log row
//        // and value is corresponded to the status of radio button
//        assertStep.checkIfRadioButtonsLogIsDisplayed(data.getRadioCheckboxesText());
//        // 7. Assert that
//        // for dropdown there is a log row
//        // and value is corresponded to the selected value.
//        assertStep.checkIfColorOptionsLogIsDisplayed(data.getColor());
//    }
//}
