package com.epam.tc.hw4.failing;

import com.epam.tc.hw4.AbstractBaseTest;
import com.epam.tc.hw4.ex1.FirstExerciseData;
import com.epam.tc.hw4.ex1.FirstExerciseUtil;
import com.epam.tc.hw4.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Homework 4")
@Story("Failing version of exercise 1 test")
public class FailingTest extends AbstractBaseTest {

    @Test
    public void failTest() {

        FirstExerciseData data = new FirstExerciseData();
        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        FirstExerciseUtil util = new FirstExerciseUtil(driver, wait, indexPage);
        // 2. Assert Browser title
        util.checkIfTitleCorrect("");
    }
}
