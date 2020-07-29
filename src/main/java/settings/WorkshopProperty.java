package settings;

import org.aeonbits.owner.Config;

public interface WorkshopProperty extends Config {

    @DefaultValue("firefox")
    @Key("browser")
    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("selenoid.url")
    String selenoidUrl();

}
