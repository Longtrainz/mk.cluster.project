package pages.search;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Getter
public class BrandSearchResultsPage extends SearchPageBasic {


    private SelenideElement viewAllButton = $(".nav-all");

    public BrandSearchResultsPage() {
        if (viewAllButton.exists()) {
            viewAllButton.click();
        }

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

}
