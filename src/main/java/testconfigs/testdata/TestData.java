package testconfigs.testdata;

import utils.RandomGenerator;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Protocol.HTTPS;
import static testconfigs.testdatamanagers.TestSiteManager.getNewTestSiteUrl;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;
import static testconfigs.testdatamanagers.TestUserManager.getEmail;
import static testconfigs.testdatamanagers.TestUserManager.getPassword;

public class TestData {
    public static String httpSite = getNewTestSiteUrl(HTTP);
    public static String httpsSite = getNewTestSiteUrl(HTTPS);
    public static String testSite = getOldTestSiteUrl(HTTPS);
    public static String newHttpSitePattern = "http://seleniumtest";
    public static String newHttpsSitePattern = "https://seleniumtest";
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

    public static String testEmail = getEmail();
    public static String testPass = getPassword();
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
