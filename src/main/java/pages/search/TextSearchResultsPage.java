package pages.search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TextSearchResultsPage extends SearchPageBasic {

    private SelenideElement searchField = $("form[action*='search'] > .ss");


    public TextSearchResultsPage() {
        pageHeader.shouldHave(Condition.text("Поиск по сайту"));
        searchField.shouldBe(Condition.visible);
    }


}
