package pages.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchPageBasic {

    protected ElementsCollection allProducts = $$(".block-tov");
    protected SelenideElement pageHeader = $(By.tagName("h1"));

}
