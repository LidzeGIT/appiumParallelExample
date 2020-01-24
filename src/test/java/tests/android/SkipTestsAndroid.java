package tests.android;

import pages.android.TutorialScreen;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import driver.Core;

//todo это как вообще? Тест наследник кора?
public class SkipTestsAndroid extends Core {

    @Test
    public void skipTutorialScreenAndroid() throws InterruptedException {
        //todo депрекейтид методы?
        AndroidDriver driver = (AndroidDriver) this.driver;

        TutorialScreen tutorialScreen = new TutorialScreen();
        tutorialScreen.clickOkButton();
        tutorialScreen.fillEmailField();
        tutorialScreen.fillPasswordField();
        tutorialScreen.clickSignInButton();
        Thread.sleep(10000);
        System.out.println("Hello, World!!! and " + getPlatformVar() + " devices");
    }
}
