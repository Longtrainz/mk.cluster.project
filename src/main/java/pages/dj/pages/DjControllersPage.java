package pages.dj.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class DjControllersPage extends MainPage {

    public DjControllersPage() {
        checkAllIfExists();
    }

    @Step("Filter results by amount of channels {filter}")
    public DjControllersPage filterByAmountOfChannels(CHANNELS_FILTER filter) {
        filter.getLocator().scrollIntoView(true).click();
        $(".filter-wide > button").click();
        return this;
    }


    @Getter
    public enum CHANNELS_FILTER {
        ONE($("div[data-val='1'] > label")),
        TWO($("div[data-val='2'] > label")),
        FOUR($("div[data-val='4'] > label")),
        EIGHT($("div[data-val='8'] > label")),
        SIXTEEN($("div[data-val='16'] > label"));

        private SelenideElement locator;

        CHANNELS_FILTER(SelenideElement locator) {
            this.locator = locator;
        }
    }

}
