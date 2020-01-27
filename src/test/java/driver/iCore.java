package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface iCore {

    RemoteWebDriver setup(String deviceName, String platformVersion, String udid) throws Exception;

    void teardown();
}
