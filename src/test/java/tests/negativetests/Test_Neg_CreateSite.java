package tests.negativetests;

import actions.Verifier;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.AddNewSitePage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.ErrorMessages;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

import static com.selenium.enums.Protocol.HTTP;
import static com.selenium.enums.Server.P2B;
import static com.selenium.utils.TextGetter.textOf;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

@Listeners(LogListener.class)
public class Test_Neg_CreateSite extends BaseTestClass {

    @Test(groups = {"negative", "new site"})// checks all error messages on page "Add New Website
    public void createSiteNegativeTest() throws Exception {
        LogInPage logInPage = new LogInPage(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        Verifier verifier = new Verifier();

        ErrorMessages errorMessages = new ErrorMessages();
        String existingSite = getOldTestSiteUrl(HTTP);
        String siteLang;

        HashMap<String, String> selectProtocol = errorMessages.getSelectProtocol();
        HashMap<String, String> siteExists = errorMessages.getSiteExists();

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        AddNewSitePage addNewSitePage = mainAdminPage.clickAddNewSiteButton();
        String btnColorBefore = addNewSitePage.getHttpsBtn().getCssValue("border");
        System.out.println(btnColorBefore);
        addNewSitePage.clickAdd();
        String btnColorAfter = addNewSitePage.getHttpsBtn().getCssValue("border");
        System.out.println(btnColorAfter);

        verifier.assertFalse(btnColorBefore.equals(btnColorAfter), "Protocol buttons are not colored in red");

        //checks error "HTTP highlight"
        for (int i = 1; i <= langs.size(); i++) {

            siteLang = headerMenu.checkLanguage();
            verifier.assertEquals(textOf(addNewSitePage.getErrorProtocolMsg()), selectProtocol.get(siteLang), " Protocol not selected error does not match");
            //checks error "Select protocol"
            if (i == langs.size() || TestServerConfiguretion.iTest.equals(P2B)) break;

            headerMenu.switchLanguage(i);
        }
        if (langs.size() > 1) {
            headerMenu.switchLanguage();
        }
        addNewSitePage.clearDomainInput()
                .setDomain(existingSite)
//                .selectHTTPprotocol()
                .clickAdd();
        verifier.assertTrue(addNewSitePage.getErrorSiteExistsMsg().isDisplayed(), "Site exists error is not displayed");

        for (int c = 1; c <= langs.size(); c++) {
            siteLang = headerMenu.checkLanguage();
            verifier.assertEquals(textOf(addNewSitePage.getErrorSiteExistsMsg()), siteExists.get(siteLang), "Site exists error does not match");
            //checks error "Site with such url already exists."
            if (c == langs.size()) break;

            headerMenu.switchLanguage(c);
        }
        addNewSitePage.uploadIcon(TestData.bigIcon);
        verifier.assertTrue(addNewSitePage.getIconTooBigError().isDisplayed(), "Icon error is not displayed");

        //checks error "Too big size of picture"

        verifier.assertTestPassed();

    }
}
