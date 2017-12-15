import actions.Verifier;
import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.ErrorMessages;
import testdata.TestData;
import testrestrictions.BetaFeatures;

import java.util.HashMap;
import java.util.List;

@Listeners(LogListener.class)
public class Test_Neg_EditUTM extends BaseTestClass {

    @Test(groups = {"negative", "UTM"})
    public void editUTMNegative() {
        LogInPage logInPage = new LogInPage(driver);
        ConfigTest configTest = new ConfigTest();
        HeaderMenu headerMenu = new HeaderMenu(driver);
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();
        String siteLang;

        if (BetaFeatures.verifyBetaToTest("UTM")) {
            HashMap<String, String> requiredField = errorMessages.getRequiredField();
            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            List<WebElement> langs = headerMenu.getAvailableLanguages();
            langs.get(0).click();
            SiteSettingsPage siteSettingsPage = mainAdminPage.openSite(configTest.getTestSiteUrl())
                    .openSiteSettingsPage()
                    .clickEditUTM()
                    .clearUTMTags();
            siteLang = headerMenu.checkLanguage();

            for (int i = 1; i <= langs.size(); i++) {
                verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.reqFielsUTMsource)).getText(), requiredField.get(siteLang));
                verifier.assertEquals(siteSettingsPage.reqFielsUTMmedium.findElement(driver).getText(), requiredField.get(siteLang));

                if (i == langs.size()) break;

                headerMenu.switchLanguage(i);
                siteLang = headerMenu.checkLanguage();
            }
            for (int i = 0; i <= 22; i++) {
                siteSettingsPage.setUTM_source("a")
                        .setUTM_medium("a");
            }
            siteSettingsPage.saveNewUTM();
            verifier.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText().length() < 21);
            verifier.assertTrue(siteSettingsPage.UTMmedium.findElement(driver).getText().length() < 21);
        }
        verifier.assertTestPassed();
    }
}
