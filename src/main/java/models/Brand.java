package models;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public enum Brand {

    ABLETON("ABLETON",$("div[data-href='/ableton/']")),
    FL_STUDIO("FL STUDIO",$("div[data-href='/fl-studio/']")),
    AKAI("AKAI",$("div[data-href='/akai/']")),
    SONY("SONY PRO",$("div[data-href='/sony-pro/']")),
    SEHNHEISER("SENNHEISER",$("div[data-href='/sennheiser/']")),
    AKG("AKG",$("div[data-href='/akg/']")),
    PRODIPE("PRODIPE",$("div[data-href='/prodipe/']")),
    ARTURIA("ARTURIA",$("div[data-href='/arturia/']"));

    private String name;
    private SelenideElement locator;

    Brand(String name,SelenideElement locator) {
        this.name = name;
        this.locator = locator;
    }
}
