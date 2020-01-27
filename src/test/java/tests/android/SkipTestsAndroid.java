package tests.android;

import pages.android.TutorialScreen;
import org.testng.annotations.Test;
import stepdefs.MainMethods;

public class SkipTestsAndroid {

    @Test
    public void skipTutorialScreenAndroid() throws InterruptedException {
        new MainMethods().setupDriver();

        TutorialScreen tutorialScreen = new TutorialScreen();
        tutorialScreen.clickOkButton();
        tutorialScreen.fillEmailField();
        tutorialScreen.fillPasswordField();
        tutorialScreen.clickSignInButton();
        Thread.sleep(10000);
    }
}
