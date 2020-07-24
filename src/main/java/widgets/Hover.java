package widgets;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class Hover {

    SelenideElement element;

    public Hover(By elementLocator) {
        element = $(elementLocator).hover();
    }
}
