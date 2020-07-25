package pages.search;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class SearchPageBasic extends MainPage {

    protected SelenideElement pageHeader = $(By.tagName("h1"));

}
