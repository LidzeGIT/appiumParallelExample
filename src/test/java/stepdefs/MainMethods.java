package stepdefs;

import driver.androidDriver;
import driver.iosDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

public class MainMethods extends TestNG {

    private WebDriver driver;
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_Android = "android";

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

    @Before("")
    public void setupDriver() {

        if (isAndroid()) {
            this.driver = new androidDriver().getDriver();
        } else if (isIOS()) {
            this.driver = new iosDriver().getDriver();
        }
    }

    public WebElement waitForElementPresent(By element, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public int getAmountOfElements(By element) {
        List elements = driver.findElements(element);
        return elements.size();
    }

    public boolean isElementPresent(By element) {
        return getAmountOfElements(element) > 0;
    }

    public void waitForElementAndClick(By elementBy, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(elementBy, error_message, timeoutInSeconds);
        element.click();
    }

    public void waitForElementAndSendKeys(By elementBy, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(elementBy, error_message, timeoutInSeconds);
        element.sendKeys(value);
    }

    public void swipeUp() {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            //магические числа были вычислены
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action.
                    longPress(PointOption.point(x, start_y))
                    .waitAction()
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }


    public void swipeUpToFindElement(By element, String error_message, int max_swipes) {
        int already_swipes = 0;
        while (driver.findElements(element).size() == 0) {
            if (already_swipes > max_swipes) {
                waitForElementPresent(element,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }
            already_swipes++;
        }
    }
}