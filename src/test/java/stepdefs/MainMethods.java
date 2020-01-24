package stepdefs;

import driver.Core;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainMethods {

    public RemoteWebDriver driver;

    public MainMethods(RemoteWebDriver driver) {
        this.driver = driver;
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