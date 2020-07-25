package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.Brand;
import models.Product;
import pages.search.BrandSearchResultsPage;
import pages.search.TextSearchResultsPage;
import widgets.Hover;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
@Slf4j
public class MainPage {


    private SelenideElement viewAllButton = $(".nav-all");

    public void checkAllIfExists(){
        if(viewAllButton.exists()){
            viewAllButton.click();
        }
    }

    protected ElementsCollection allProducts = $$(".block-tov");

    private SelenideElement siteSearchField = $(".search1 * input");
    private SelenideElement brandSearchField = $(".search2 * input");


    Hover djHover = new Hover($(".menu * a[href='/dj-equipment/']"));


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

    @Step("Get list of all products from page")
    public List<Product> getListOfProductsFromPage() {
        return allProducts.stream().map(mapRowToProduct()).collect(Collectors.toList());
    }

    private Function<SelenideElement, Product> mapRowToProduct() {
        return row -> {
            boolean isHotSale = row.$("span[class='spez spez-red']").exists();

            int position = 0;
            try {
                SelenideElement valueHolder = row.$("span[class='spez spez-left spez-red']");
                if (valueHolder.exists()) {
                    position = Integer.parseInt(valueHolder.getText());
                }
            } catch (NumberFormatException ex) {
                log.warn("Element is not in top3 list, so setting position to 0");
            }

            int oldPrice = 0;
            try {
                SelenideElement valueHolder = row.$(".tov-price > .price_old");
                if (valueHolder.exists()) {
                    oldPrice = Integer.parseInt(valueHolder.getText().replaceAll(" грн", ""));
                }
            } catch (NumberFormatException ex) {
                log.warn("There's no provided price, need to ask support team");
            }

            int newPrice = 0;
            try {
                SelenideElement valueHolder = row.$(".tov-price > .price_new");
                if (valueHolder.exists()) {
                    newPrice = Integer.parseInt(valueHolder.getText().replaceAll(" грн", ""));
                }
            } catch (NumberFormatException ex) {
                log.warn("There's no provided price, need to ask support team");
            }

            return Product.builder()
                    .position(position)
                    .priceNew(newPrice)
                    .priceOld(oldPrice)
                    .isHotSale(isHotSale)
                    .name(row.$(".bt-name").getText())
                    .build();

        };
    }

    //DJs sub-pages buttons
    protected SelenideElement djControllersButton = $(".menu * a[href='/dj-controllers/']");
    protected SelenideElement djSystemsButton = $(".menu * a[href='/dj-systems/']");
    protected SelenideElement dvsSystemsButton = $(".menu * a[href='/dvs-systems/']");
    protected SelenideElement djPlayerButton = $(".menu * a[href='/players-usb-cd/']");
    protected SelenideElement djMixersButton = $(".menu * a[href='/mixers-for-dj/']");
    protected SelenideElement djBoxesButton = $(".menu * a[href='/kits-for-dj/']");
    protected SelenideElement effectorsButton = $(".menu * a[href='/effects-control-panels/']");
    protected SelenideElement vinylPlayersButton = $(".menu * a[href='/vinyl-record-players/']");
    protected SelenideElement correctorsButton = $(".menu * a[href='/fonokorrektory/']");
    protected SelenideElement timecodesButton = $(".menu * a[href='/plastinki/']");



}
