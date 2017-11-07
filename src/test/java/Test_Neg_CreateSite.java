import actions.Verifier;
import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import testdata.ErrorMessages;
import testdata.TestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Neg_CreateSite extends SeleniumBaseClass {


    @Test(groups = { "negative", "new site" })// checks all error messages on page "Add New Website
    public void createSiteNegative() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        Verifier verifier = new Verifier();

        ConfigTest config = new ConfigTest();

        ErrorMessages errorMessages = new ErrorMessages();
        String existingSite = config.getTestSiteUrl();
        String siteLang;

        HashMap<String, String> selectProtocol = errorMessages.getSelectProtocol();
        HashMap<String, String> siteExists = errorMessages.getSiteExists();

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        AddNewSitePage addNewSitePage = mainAdminPage.clickAddNewSiteButton();
        addNewSitePage.clickAdd();

        verifier.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(addNewSitePage.errorHTTPButton)).isDisplayed());
        verifier.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(addNewSitePage.errorHTTPSButton)).isDisplayed());

        //checks error "HTTP highlight"
        for (int i = 1; i <= langs.size(); i++) {

            siteLang = headerMenu.checkLanguage();
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(addNewSitePage.protocolMessage)).getText(), selectProtocol.get(siteLang));
            //checks error "Select protocol"
            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }
        if (langs.size() > 1) {
            headerMenu.switchLanguage();
        }
        driver.findElement(addNewSitePage.domainInput).clear();
        addNewSitePage.setDomain(existingSite);
        addNewSitePage.selectHTTPprotocol();
        addNewSitePage.clickAdd();
        verifier.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(addNewSitePage.existsError)).isDisplayed());

        for (int c = 1; c <= langs.size(); c++) {
            siteLang = headerMenu.checkLanguage();
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(addNewSitePage.existsError)).getText(), siteExists.get(siteLang));
            //checks error "Site with such url already exists."
            if (c == langs.size()) break;

            headerMenu.switchLanguage(c);
        }
        addNewSitePage.uploadIcon(TestData.bigIcon);
        verifier.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(addNewSitePage.iconError)).isDisplayed());

        //checks error "Too big size of picture"
        headerMenu.logout();
        verifier.assertTestPassed();

    }
}
