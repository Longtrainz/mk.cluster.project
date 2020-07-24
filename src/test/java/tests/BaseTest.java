package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(TextReportExtension.class)
public class BaseTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "firefox";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    public MainPage openMainPage() {
        open("https://prometheus.org.ua/");
        return new MainPage();
    }

}
