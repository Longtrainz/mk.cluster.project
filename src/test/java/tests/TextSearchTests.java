package tests;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TextSearchTests extends BaseTest{


    @Test
    @DisplayName("Check that search by invalid text works properly and shows valid results")
    @Description("Check that search by invalid text works properly and shows valid results")
    void checkValidSearch() {
        openMainPage().searchForSomethingByTextAndCheckResults("AKAI")
                .getAllProducts()
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    @DisplayName("Check that search by invalid text works properly and shows no results")
    @Description("Check that search by invalid text works properly and shows no results")
    void checkInvalidSearch() {
        openMainPage().searchForSomethingByTextAndCheckResults(FAKER.rickAndMorty().quote())
                .getAllProducts()
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
