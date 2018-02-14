package webdriverconfiger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.Wait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

public class WebDriverManager {
    public WebDriver driver;
    public Wait<WebDriver> wait;

    private StringBuilder path = new StringBuilder("src/main/resources/WebDrivers/");

    public WebDriver getDriver(String browser) {
        return browser.equalsIgnoreCase("chrome") ? getChromeDriver() :
                browser.equalsIgnoreCase("firefox") ? getFirefoxDriver() :
                        browser.equalsIgnoreCase("opera") ? getOperaDriver() : null;
    }


    private WebDriver getChromeDriver() {
        String os = getOS();
        String driverType = os == "win" ? "/chromedriver.exe" : "/chromedriver";
        System.setProperty("webdriver.chrome.driver", new File(path.append("chromedriver 2.33/" + os + driverType).toString()).getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 1);
        chromePrefs.put("download.default_directory", "src/main/resources");
//        options.addArguments("--headless", "--disable-gpu");//
        options.setExperimentalOption("prefs", chromePrefs);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        driver = WebDriverPool.DEFAULT.getDriver(options);
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver",
                new File(path.append("geckodriver 0.19.1/geckodriver.exe").toString()).getAbsolutePath());
        driver = WebDriverPool.DEFAULT.getDriver("firefox");
        return driver;
    }

    private WebDriver getOperaDriver() {
        System.setProperty("webdriver.opera.driver",
                new File(path.append("operadriver 2.27/operadriver.exe").toString()).getAbsolutePath());
        OperaOptions options = new OperaOptions();
        options.setBinary("D:\\Program Files\\Opera\\49.0.2725.34\\opera.exe");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 1);
        chromePrefs.put("download.default_directory", "src/main/resources");
        options.setExperimentalOption("prefs", chromePrefs);
        driver = WebDriverPool.DEFAULT.getDriver(options);
        return driver;
    }

    private String getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println("RUNNING ON " + osName + "..................................");
        return (osName.indexOf("win") >= 0) ? "win" :
                ((osName.indexOf("mac") >= 0) ? "mac" :
                        (((osName.indexOf("nix") >= 0) || (osName.indexOf("nux") >= 0) || (osName.indexOf("aix") > 0)) ? "linux" : null));
    }

    public void quitDriver(WebDriver driver) {
        WebDriverPool.DEFAULT.dismissDriver(driver);
    }

    public void quitAllDrivers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
