package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(TextReportExtension.class)
public class BaseTest {

    public static final Faker FAKER = new Faker();
    public static final SoftAssertions SA = new SoftAssertions();

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "firefox";
        Configuration.timeout = 5000L;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    public MainPage openMainPage() {
        open("https://www.prodj.com.ua/");
        return new MainPage();
    }

}
