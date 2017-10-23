
import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.MailService;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import java.util.List;

public class Test_Pos_NewSiteMailsMultiLanguage extends SeleniumBaseClass {

    @Test(groups = {"mails", "new site"})
    public void testCreateSiteMails() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        AddNewSitePage addNewSitePage = new AddNewSitePage(driver, wait);
        LogInPage logInPage = new LogInPage(driver, wait);
        ConfigTest configTest = new ConfigTest();
        Verifier verifier = new Verifier();

        int emailNumber = Integer.valueOf(configTest.getEmailNumber()) - 2;
        String email = "grovitek+" + emailNumber + "@gmail.com";
        String pass = configTest.getPassword();
        String siteLang;

        MainAdminPage mainAdminPage = logInPage.login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        driver.navigate().refresh();
        siteLang = headerMenu.checkLanguage();
        for (int i = 1; i <= langs.size(); i++) {
            String siteUrl = "http://" + RandomGenerator.nextString() + ".com";

            addNewSitePage.createSite(siteUrl);
            String message = MailService.getCreateSitedMail();
            System.out.println(message);
            verifier.assertTrue(verifier.verifyCreateSiteMail(message, ConfigTest.iTest, siteLang));
            headerMenu.clickLogo();
            mainAdminPage.verifySitePresent(siteUrl);
            if (i == langs.size()) {
                headerMenu.logout();
                break;
            }
            headerMenu.switchLanguage(i);
            siteLang = headerMenu.checkLanguage();
        }

        verifier.assertTestPassed();
    }
}
