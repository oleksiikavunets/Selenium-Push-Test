import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import testdata.CreateSiteMails;
import testdata.PasswordRecoveryMails;
import testdata.RegistrationMails;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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


    @Test
    public void checkNewSiteMail(){
        CreateSiteMails createSiteMails = new CreateSiteMails();
        HashMap<String, List> mails = createSiteMails.getMails("prod");
        List<String> mailEn = mails.get("en");
        List<String> mailRu = mails.get("ru");
        List<String> mailPl = mails.get("pl");
        List<String> mailDe = mails.get("de");

        System.out.println("EN:");
        for(String m: mailEn){
            System.out.println(m);
        }

        System.out.println("RU:");
        for(String m: mailRu){
            System.out.println(m);
        }

        System.out.println("PL:");
        for(String m: mailPl){
            System.out.println(m);
        }

        System.out.println("DE:");
        for(String m: mailDe){
            System.out.println(m);
        }
    }
@Test
public void checkRegMail(){
    RegistrationMails registrationMails = new RegistrationMails();
    HashMap<String, List> mails = registrationMails.getMails("prod");
    List<String> mailEn = mails.get("en");
    List<String> mailRu = mails.get("ru");
    List<String> mailPl = mails.get("pl");
    List<String> mailDe = mails.get("de");

    System.out.println("EN:");
    for(String m: mailEn){
        System.out.println(m);
    }

    System.out.println("RU:");
    for(String m: mailRu){
        System.out.println(m);
    }

    System.out.println("PL:");
    for(String m: mailPl){
        System.out.println(m);
    }

    System.out.println("DE:");
    for(String m: mailDe){
        System.out.println(m);
}}
    @Test
    public void checkPassMail(){
        PasswordRecoveryMails passwordRecoveryMails = new PasswordRecoveryMails();
        HashMap<String, List> mails = passwordRecoveryMails.getMails("prod");
        List<String> mailEn = mails.get("en");
        List<String> mailRu = mails.get("ru");
        List<String> mailPl = mails.get("pl");
        List<String> mailDe = mails.get("de");

        System.out.println("EN:");
        for(String m: mailEn){
            System.out.println(m);
        }

        System.out.println("RU:");
        for(String m: mailRu){
            System.out.println(m);
        }

        System.out.println("PL:");
        for(String m: mailPl){
            System.out.println(m);
        }

        System.out.println("DE:");
        for(String m: mailDe){
            System.out.println(m);
        }
    }

}
