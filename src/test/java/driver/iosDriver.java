package driver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;

public class iosDriver extends Settings implements iCore {
    private static final String URL = getURL();

    private RemoteWebDriver driver;

    public RemoteWebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceName", "platformVersion", "udid"})
    @Override
    public RemoteWebDriver setup(String deviceName, String platformVersion, String udid) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("xcodeOrgId", "1234567890");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability("newCommandTimeout", 60);
        capabilities.setCapability("app", new File("apks/123").getAbsolutePath());
        capabilities.setCapability("fullReset", "false");

        System.out.println("Device name is: " + deviceName);
        System.out.println("Platform version is: " + platformVersion);
        System.out.println("Device UDID is: " + udid);
        System.out.println("Path to file = " + new File("apks/123").getAbsolutePath());

        this.driver = new IOSDriver(new URL(URL), capabilities);
        ((IOSDriver) driver).unlockDevice();
        return driver;
    }

    @Override
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
