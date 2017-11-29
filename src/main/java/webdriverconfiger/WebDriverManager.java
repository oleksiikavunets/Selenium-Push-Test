package webdriverconfiger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

public class WebDriverManager {
    public static WebDriver driver;
    public static Wait<WebDriver> wait;

    public static WebDriver getDriver(String browser) {
        switch (browser){
            case ("chrome"):
                driver = getChromeDriver();
                break;
            case ("firefox"):
                driver = getFirefoxDriver();
                break;
            case ("opera"):
                driver = getOperaDriver();
                break;
        }
        return driver;
    }


    public static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", new File("src/main/resources/WebDrivers/chromedriver 2.33/chromedriver.exe").getAbsolutePath());
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 1);
        chromePrefs.put("download.default_directory", "src/main/resources");
        options.setExperimentalOption("prefs", chromePrefs);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = WebDriverPool.DEFAULT.getDriver(capabilities);
        return driver;
    }

    public static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", new File("src/main/resources/WebDrivers/geckodriver 0.19 win64/geckodriver.exe").getAbsolutePath());
        driver = WebDriverPool.DEFAULT.getDriver("firefox");
        return driver;
    }
    public static WebDriver getOperaDriver(){
        System.setProperty("webdriver.opera.driver", new File("src/main/resources/WebDrivers/operadriver 2.27/operadriver.exe").getAbsolutePath());
        OperaOptions options = new OperaOptions();
        options.setBinary("D:\\Program Files\\Opera\\49.0.2725.34\\opera.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 1);
        chromePrefs.put("download.default_directory", "src/main/resources");
        options.setExperimentalOption("prefs", chromePrefs);
        driver = WebDriverPool.DEFAULT.getDriver(options);
        return driver;
    }

    public static void quitDriver(WebDriver driver){
        WebDriverPool.DEFAULT.dismissDriver(driver);
    }

    public static void quitAllDrivers(){
        WebDriverPool.DEFAULT.dismissAll();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
