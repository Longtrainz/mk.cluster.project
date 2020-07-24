package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import models.Brand;
import pages.search.BrandSearchResultsPage;
import pages.search.TextSearchResultsPage;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class MainPage {

    private SelenideElement siteSearchField = $(".search1 * input");
    private SelenideElement brandSearchField = $(".search2 * input");

    @Step("Search for products by brand {brand}.")
    public BrandSearchResultsPage searchByBrand(Brand brand) {
        brandSearchField.click();
        brand.getLocator().scrollIntoView(false).click();
        return new BrandSearchResultsPage();
    }

    @Step("Search by text {text} and check results")
    public TextSearchResultsPage searchForSomethingByTextAndCheckResults(String text){
        siteSearchField.sendKeys(text);
        siteSearchField.pressEnter();
        return new TextSearchResultsPage();
    }



}
