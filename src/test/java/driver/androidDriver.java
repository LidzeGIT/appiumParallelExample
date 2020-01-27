package driver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;

public class androidDriver extends Settings implements iCore {
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

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.allgoritm.youla");
        capabilities.setCapability("appActivity", "com.allgoritm.youla.AppInitActivity");
        capabilities.setCapability("app", new File("apks/com.allgoritm.youla.apk").getAbsolutePath());
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("fullReset", "true"); //вкл если нужно чтобы apk устанавливался с 0 каждый раз, http://appium.io/docs/en/writing-running-appium/other/reset-strategies/index.html
        capabilities.setCapability("clearSystemFiles", "true");

        System.out.println("Device name is: " + deviceName);
        System.out.println("Platform version is: " + platformVersion);
        System.out.println("Device UDID is: " + udid);
        System.out.println("Path to file = " + new File("apks/com.allgoritm.youla.apk").getAbsolutePath());

        return this.driver = new AndroidDriver(new URL(URL), capabilities);
    }

    @Override
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
