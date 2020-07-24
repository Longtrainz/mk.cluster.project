package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import models.Brand;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class MainPage {

    private SelenideElement siteSearchField = $(".search1 * input");
    private SelenideElement brandSearchField = $(".search2 * input");

    @Step("Search for products by brand {brand}.")
    public SearchResultsPage searchByBrand(Brand brand) {
        brandSearchField.click();
        brand.getLocator().scrollIntoView(false).click();
        return new SearchResultsPage();
    }

}
