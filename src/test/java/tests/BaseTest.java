package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;
import settings.WorkshopProperty;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(TextReportExtension.class)
public class BaseTest {

    static final Faker FAKER = new Faker();
    static final SoftAssertions SA = new SoftAssertions();

    public static final WorkshopProperty WP = ConfigFactory.create(WorkshopProperty.class, System.getProperties());

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = WP.browser();
        Configuration.timeout = 5000L;
        Configuration.startMaximized = true;

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("enableVNC", true);
        dc.setCapability("enableVideo", false);

        Configuration.browserCapabilities = dc;

        Configuration.remote = WP.selenoidUrl();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }


    public MainPage openMainPage() {
        open(WP.baseUrl());
        return new MainPage();
    }

}
