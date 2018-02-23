package tests.mailtests;

import actions.Verifier;
import com.selenium.MailService;
import utils.RandomGenerator;
import common.BaseTestClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testutils.Listeners.LogListener;

import java.util.List;

import static com.selenium.enums.Server.P2B;
import static testconfigs.testdata.TestData.newHttpSitePattern;
import static testconfigs.testdatamanagers.TestUserManager.getEmail;
import static testconfigs.testdatamanagers.TestUserManager.getPassword;

@Listeners(LogListener.class)
public class Test_Pos_NewHttpSiteMailsMultiLanguage extends BaseTestClass {

    @Test(groups = {"mails", "new site"}, singleThreaded = true, threadPoolSize = 1)
    public void createHttpSiteMailsTest() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_NewHttpSiteMailsMultiLanguage.class);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        Verifier verifier = new Verifier();

        String email = getEmail();
        String pass = getPassword();
        String siteLang;

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();
            String siteUrl = newHttpSitePattern + RandomGenerator.nextString() + ".com";

            new AddNewSitePage(driver).createSite(siteUrl);
            String message = MailService.getCreatedSiteMail();
            Log.info(message);
            verifier.assertTrue(verifier.verifyReceivedMail(message, siteLang));
            headerMenu.clickLogo();
            mainAdminPage.verifySitePresent(siteUrl);
            if (i == langs.size() || TestServerConfiguretion.iTest.equals(P2B)) {
                break;
            }
            headerMenu.switchLanguage(i);
        }
        verifier.assertTestPassed();
    }
}
