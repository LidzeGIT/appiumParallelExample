package pages.android;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import stepdefs.MainMethods;

public class TutorialScreen extends MainMethods {

    public TutorialScreen(RemoteWebDriver driver) {
        super(driver);
    }

    By ok_button = By.id("com.allgoritm.youla:id/ok");
    By email_field = By.xpath("//*[@resource-id = 'field_email']");
    By pass_field = By.xpath("//*[@resource-id = 'field_password']");
    By sighIn_button = By.xpath("//android.widget.Button[@text = 'Sign in']");
    By requestLocation_button = By.id("com.allgoritm.youla:id/requestLocationButton");
    By requestLocation_button_whileAppIsOpen = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    By shading_view = By.id("com.allgoritm.youla:id/shading_view");
    By avatar_button = By.id("com.allgoritm.youla:id/avatar_iv");
    By menu_button = By.id("com.allgoritm.youla:id/menu_profile");
    By setting_row = By.id("com.allgoritm.youla:id/settings_row");
    By username = By.id("com.allgoritm.youla:id/user_name_tv");

    public void clickOkButton() {
        waitForElementAndClick(
                ok_button,
                "Couldn't find ok button",
                5);

    }
    public void fillEmailField() {
        waitForElementAndClick(
                email_field,
                "Couldn't find email_field",
                5);
        waitForElementAndSendKeys(
                email_field,
                "1234",
                "Couldn't fill email_field",
                5);
    }

    public void fillPasswordField() {
        waitForElementAndClick(
                pass_field,
                "Couldn't find pass_field",
                5);
        waitForElementAndSendKeys(
                pass_field,
                "12345",
                "Couldn't fill pass_field",
                5);
    }

    public void clickSignInButton() {
        waitForElementAndClick(
                sighIn_button,
                "Couldn't find sign in button",
                5);
    }
}