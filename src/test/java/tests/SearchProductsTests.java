package tests;

import io.qameta.allure.Description;
import models.Brand;
import models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.search.BrandSearchResultsPage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static models.Brand.*;
import static org.assertj.core.api.Assertions.assertThat;

class SearchProductsTests extends BaseTest {


    @Test
    @DisplayName("Check that FL_Stuio brand has just 3 items.")
    void checkAmountOfProductsForProvidedBrand() {
        openMainPage().searchByBrand(Brand.FL_STUDIO).getAllProducts().shouldHaveSize(3);
    }

    @Test
    @DisplayName("Check products for prodipe")
    @Description("Check products for prodipe")
    void checkProductsForProdipe() {
        List<Product> listOfProductsFromPage = openMainPage().searchByBrand(Brand.PRODIPE).getListOfProductsFromPage();

        int listOfTopProductsSize = listOfProductsFromPage.stream().filter(product -> product.getPosition() > 0).collect(Collectors.toList()).size();
        assertThat(listOfTopProductsSize).isEqualTo(3);

        listOfProductsFromPage.forEach(product -> {
            SA.assertThat(product.getName()).isNotEmpty();
        });

        SA.assertAll();
    }

    @ParameterizedTest(name = "{displayName}. Brand = {0}, Expected = {1}")
    @MethodSource
    @DisplayName("Check that amount of products for specific brand is equals to expected")
    @Description("Check that amount of products for specific brand is equals to expected")
    void checkAmountOfProductsForEachBrand(Brand brand, int expectedSize) {
        BrandSearchResultsPage searchResultsPage = openMainPage().searchByBrand(brand);

        assertThat(searchResultsPage.getPageHeader().getText()).isEqualToIgnoringCase(brand.getName());

        List<Product> listOfProductsFromPage = searchResultsPage.getListOfProductsFromPage();
        assertThat(listOfProductsFromPage.size()).isEqualTo(expectedSize);
    }

    private static Stream<Arguments> checkAmountOfProductsForEachBrand(){
        return Stream.of(Arguments.of(ABLETON,23),
                Arguments.of(FL_STUDIO,3),
                Arguments.of(AKAI,47),
                Arguments.of(SONY,4),
                Arguments.of(SEHNHEISER,151),
                Arguments.of(AKG,63),
                Arguments.of(PRODIPE,37),
                Arguments.of(ARTURIA,46));
    }
}
