package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;

public class Core {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_Android = "android";
    private static final String URL = "http://127.0.0.1:4723/wd/hub";

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver driver;

    public String getPlatformVar() {
        return System.getenv("platform");
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_Android);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"deviceName", "platformVersion", "udid"})
    public void setup(String deviceName, String platformVersion, String udid) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (this.isAndroid()) {

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

            driver = new AndroidDriver(new URL(URL), capabilities);

            System.out.println("Device name is: " + deviceName);
            System.out.println("Platform version is: " + platformVersion);
            System.out.println("Device UDID is: " + udid);
            System.out.println("Path to file = " + new File("apks/com.allgoritm.youla.apk").getAbsolutePath());

        } else if (this.isIOS()) {

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

            driver = new IOSDriver(new URL(URL), capabilities);
            ((IOSDriver) driver).unlockDevice();

            System.out.println("Device name is: " + deviceName);
            System.out.println("Platform version is: " + platformVersion);
            System.out.println("Device UDID is: " + udid);
            System.out.println("Path to file = " + new File("apks/123").getAbsolutePath());
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}