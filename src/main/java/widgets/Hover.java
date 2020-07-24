package widgets;

import com.codeborne.selenide.SelenideElement;

public class Hover {

    private SelenideElement element;

    public Hover(SelenideElement element) {
        element.hover();
    }
}
