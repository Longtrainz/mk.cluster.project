package tests;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.dj.pages.DjControllersPage;

import java.util.stream.Stream;

import static pages.dj.pages.DjControllersPage.CHANNELS_FILTER.*;

public class DjControllerTests extends BaseTest {

    @Test
    @DisplayName("Make sure that all controllers are present")
    @Description("Make sure that all controllers are present")
    void getListOfControllers() {
        openDJControllersPage()
                .getAllProducts()
                .shouldHave(CollectionCondition.sizeGreaterThan(100));
    }


    @ParameterizedTest(name = "{displayName}. Amount of channels = {0}, expected amount of products = {1}.")
    @MethodSource
    @Description("Make sure that filtering by amount of channels for controllers returns valid results")
    @DisplayName("Make sure that filtering by amount of channels for controllers returns valid results")
    void checkAmountOfControllersFilteringByAmountOfChannels(DjControllersPage.CHANNELS_FILTER filter, int amountOfProducts) {
        openDJControllersPage()
                .filterByAmountOfChannels(filter)
                .getAllProducts()
                .shouldHaveSize(amountOfProducts);
    }

    private static Stream<Arguments> checkAmountOfControllersFilteringByAmountOfChannels() {
        return Stream.of(Arguments.of(ONE, 3),
                Arguments.of(TWO, 21),
                Arguments.of(FOUR, 21),
                Arguments.of(EIGHT, 10),
                Arguments.of(SIXTEEN, 1));
    }


    @Step("Open DJ Controllers Page.")
    private DjControllersPage openDJControllersPage() {
        MainPage mainPage = openMainPage();
        mainPage.getDjHover();
        mainPage.getDjControllersButton().click();
        return new DjControllersPage();
    }
}
