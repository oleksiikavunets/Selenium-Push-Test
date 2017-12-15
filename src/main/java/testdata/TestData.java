package testdata;

import com.selenium.ConfigTest;
import com.selenium.TestSiteManager;
import com.selenium.utils.RandomGenerator;

public class TestData {
    static ConfigTest configTest = new ConfigTest();
    static TestSiteManager testSiteManager = new TestSiteManager();
    public static String httpSite = testSiteManager.getHttpSiteUrl();
    public static String httpsSite = testSiteManager.getHttpsSiteUrl();
    public static String testSite = configTest.getTestSiteUrl();
    public static String newSitePattern = "http://seleniumtest";
    public static String welcomeMessageTitle = "Welcome Message Title: " + RandomGenerator.nextString();
    public static String welcomeMessageText = "Welcome Message Text: " + RandomGenerator.nextString();
    public static String pushTitle = "Push Title: " + RandomGenerator.nextString();
    public static String pushText = "Push Text: " + RandomGenerator.nextString();
    public static String button1Name = "Button 1";
    public static String button2Name = "Button 2";

    public static String icon = "src/main/resources/imgs/selenium.jpg";
    public static String bigIcon = "src/main/resources/imgs/forest-wallpaper-1920x1200-004.jpg";
    public static String bigImage = "src/main/resources/imgs/550Х430.jpg";

    public static String alias = "Alex";
    public static String tag = "FF";
    public static String url = "http://business.kyivstar.ua/kr-620/businesslarge/";
    public static String browser = "Chrome";
    public static String country = "Ukraine";
    public static String city = "Kiev";
    public static String newAlias = RandomGenerator.nextString();
    public static String newTag = RandomGenerator.nextString();

    public static String testEmail = "grovitek+" + (Integer.valueOf(configTest.getEmailNumber()) - 2) + "@gmail.com";
    public static String testPass = configTest.getPassword();
    public static String email = "mpstestdepartment@gmail.com";
    public static String pass = "tttt1111";
    public static String pass2 = "qqqq1111";


    public static String inValidEmail = "wrongemail@email.pms";
    public static String invalidPass = "1111";

    public static String debtorEmail = "smokepayment@gmail.com";
    public static String utm_source = "test-source";
    public static String utm_medium = "test-medium";
    public static String utm_campaign = "test-campaign";

    public static String tagListName = "taglist" + RandomGenerator.nextString();

}
