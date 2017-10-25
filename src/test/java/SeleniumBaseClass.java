import com.selenium.ConfigTest;
import com.selenium.GravitecServer;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;
import pageobjects.HeaderMenu;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Rayant on 18.04.2017.
 */
public class SeleniumBaseClass {


    protected static WebDriver driver;
    public static GravitecServer gravitecServer;

    protected static Properties prop = new Properties();
    protected Wait<WebDriver> wait;


    @BeforeTest(alwaysRun = true)
    public static void initializeGravitec() {
        ConfigTest config = new ConfigTest();
        String hostUrl = config.getHostUrl();
        String apiUrl = config.getApiUrl();
        String adminLogin = config.getAdminLogin();
        String adminPass = config.getAdminPass();
        String testSiteUrl = config.getTestSiteUrl();


        int port = config.getPort();
        int directPort = config.getDirectPort();
        gravitecServer = new GravitecServer(port, directPort);
        gravitecServer.setHostUrl(hostUrl);
        gravitecServer.setApiUrl(apiUrl);


        //System.out.println(gravitecServer.getPort());
        //gravitecServer.login(adminLogin,adminPass);
        //gravitecServer.setTestSiteUrl(testSiteUrl);
        //gravitecServer.setTestSiteId(gravitecServer.getSiteId(testSiteUrl)); */

    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void configureWebDriver(@Optional("chrome") String browser) {
        if(driver == null) {
            driver = getConfiguredWebDriver(browser, false);
        }
        wait = new FluentWait<WebDriver>(driver).withMessage("Element was not found").withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @Parameters("browser")
    @AfterClass( alwaysRun = true)
    public void closeWebDriver(@Optional("chrome") String browser) throws AWTException {
            try {
                driver.findElement(new HeaderMenu(driver, wait).logOutButton).click();
            }catch (org.openqa.selenium.NoSuchElementException noEl){
            }catch (org.openqa.selenium.StaleElementReferenceException staleEl){
            }

        if (browser.equalsIgnoreCase("chrome")) {
//            driver.close();
//            driver.quit();
        }else if(browser.equalsIgnoreCase("firefox")) {
            driver.close();
            driver.quit();
        }else if(browser.equalsIgnoreCase("opera")){

//            try {
//                Runtime.getRuntime().exec("taskkill/f /im opera.exe");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

    }

    @Parameters("browser")
    @AfterSuite
    public void endTest(@Optional("chrome") String browser){
        driver.close();
        driver.quit();
  }

    protected static WebDriver getConfiguredWebDriver(String browser, boolean addBlockAnabled) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", new File("src/main/resources/WebDrivers/chromedriver 2.33/chromedriver.exe").getAbsolutePath());
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("start-maximized");
            if (addBlockAnabled)
                options.addExtensions(new File("src/main/resources/WebDrivers/Adblock_Plus_v_1_13_3.crx"));
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
            driver = new ChromeDriver(capabilities);
        } else if (browser.equalsIgnoreCase("firefox")) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            System.setProperty("webdriver.gecko.driver", new File("src/main/resources/WebDrivers/geckodriver 0.19 win64/geckodriver.exe").getAbsolutePath());
            FirefoxOptions options = new FirefoxOptions();
            ProfilesIni profile = new ProfilesIni();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.acceptInsecureCerts();
            capabilities.setJavascriptEnabled(true);
            driver = new FirefoxDriver(capabilities);
        } else if (browser.equalsIgnoreCase("opera")) {
            System.setProperty("webdriver.opera.driver", new File("src/main/resources/WebDrivers/operadriver 2.30/operadriver.exe").getAbsolutePath());
            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Program Files\\Opera\\47.0.2631.80\\opera.exe");
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_setting_values.notifications", 1);
            chromePrefs.put("download.default_directory", "D:\\work\\pushnotifications\\Selenium\\src\\main\\resources");
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new OperaDriver(options);

        }
        return driver;
    }


}