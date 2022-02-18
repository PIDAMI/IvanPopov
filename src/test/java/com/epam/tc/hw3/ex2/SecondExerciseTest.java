package com.epam.tc.hw3.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.AbstractBaseTest;
import com.epam.tc.hw3.page.objects.voids.JdiIndexPage;
import java.util.Optional;
import org.testng.annotations.Test;

public class SecondExerciseTest extends AbstractBaseTest {

    @Test
    public void secondExerciseTest() {
        JdiIndexPage indexPage = new JdiIndexPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(siteTitle);

        // 3. Perform login
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        Optional<String> userName = indexPage.profile()
                                             .getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isEqualTo(true);
        assertThat(userName.get()).isEqualTo(expectedUserName);

        // TODO 5 till end  

    }
}
