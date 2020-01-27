package driver;

public abstract class Settings {

    private static final String URL = "http://127.0.0.1:4723/wd/hub";

    public static String getURL() {
        return URL;
    }

}