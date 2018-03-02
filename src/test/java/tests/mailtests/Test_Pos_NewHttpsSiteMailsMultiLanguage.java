package tests.mailtests;

import actions.UserActions;
import actions.Verifier;
import com.selenium.MailService;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testdatamanagers.TestUserManager;
import utils.Log;
import utils.RandomGenerator;

import java.util.List;

import static com.selenium.enums.Server.P2B;

public class Test_Pos_NewHttpsSiteMailsMultiLanguage extends BaseTestClass {

    @Test
    public void createHttpsSiteMailsTest() throws Exception {

        HeaderMenu headerMenu = new HeaderMenu(driver);
        Verifier verifier = new Verifier();

        String email = new TestUserManager().getEmail();
        String pass = new TestUserManager().getPassword();
        String siteLang;

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();
            String siteUrl = TestData.newHttpsSitePattern + RandomGenerator.nextString() + ".com";

            new AddNewSitePage(driver).createSite(siteUrl);
            String message = MailService.getCreatedSiteMail();
            Log.info(message);
            verifier.assertTrue(verifier.verifyReceivedMail(message, siteLang));
            headerMenu.clickLogo();
            mainAdminPage.verifySitePresent(siteUrl);
            new UserActions(driver).deleteSite(siteUrl);
            if (i == langs.size() || TestServerConfiguretion.iTest.equals(P2B)) {
                break;
            }
            headerMenu.switchLanguage(i);
        }
        verifier.assertTestPassed();
    }
}

