
import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import testdata.TestData;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import java.util.List;

import static com.selenium.enums.Server.P2B;

@Listeners(LogListener.class)
public class Test_Pos_NewHttpSiteMailsMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "new site"}, singleThreaded = true, threadPoolSize = 1)
    public void testCreateHttpSiteMails() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_NewHttpSiteMailsMultiLanguage.class);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        ConfigTest configTest = new ConfigTest();
        Verifier verifier = new Verifier();

        int emailNumber = Integer.valueOf(configTest.getEmailNumber()) - 2;
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String pass = configTest.getPassword();
        String siteLang;

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();
            String siteUrl = TestData.newSitePattern + RandomGenerator.nextString() + ".com";

            new AddNewSitePage(driver).createSite(siteUrl);
            String message = MailService.getCreatedSiteMail();
            Log.info(message);
            verifier.assertTrue(verifier.verifyCreateSiteMail(message, siteLang));
            headerMenu.clickLogo();
            mainAdminPage.verifySitePresent(siteUrl);
            if (i == langs.size() || ConfigTest.iTest.equals(P2B)) {
                break;
            }
            headerMenu.switchLanguage(i);
        }
        verifier.assertTestPassed();
    }
}
