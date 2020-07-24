package models;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public enum Brand {

    ABLETON($("div[data-href='/ableton/']")),
    FL_STUDIO($("div[data-href='/fl-studio/']")),
    AKAI($("div[data-href='/akai/']")),
    SONY($("div[data-href='/sony-pro/']")),
    SEHNHEISER(($("div[data-href='/sennheiser/']"))),
    AKG($("div[data-href='/arturia/']")),
    PRODIPE(($("div[data-href='/prodipe/']"))),
    ARTURIA($("div[data-href='/arturia/']"));

    private SelenideElement locator;

    Brand(SelenideElement locator) {
        this.locator = locator;
    }
}
