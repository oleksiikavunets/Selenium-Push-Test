import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by Rayant on 11.04.2017.
 */
public class SeleniumSetup {

    public static WebDriver getConfiguredWebDriver(boolean addBlockAnabled) {
        System.setProperty("webdriver.gecko.driver", new File("src/main/resources/geckodriver-v0.15.0-win64/geckodriver.exe").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", new File("src/main/resources/geckodriver-v0.15.0-win64/chromedriver.exe").getAbsolutePath());
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        if (addBlockAnabled)
            options.addExtensions(new File(new File("src/main/resources/geckodriver-v0.15.0-win64/Adblock-Plus_v1.13.2.crx").getAbsolutePath()));
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_setting_values.notifications", 1);
        chromePrefs.put("download.default_directory", "D:\\work\\pushnotifications\\Selenium\\src\\main\\resources");
        options.setExperimentalOption("prefs", chromePrefs);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }
}
