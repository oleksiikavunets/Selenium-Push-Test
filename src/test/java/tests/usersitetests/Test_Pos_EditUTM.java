package tests.usersitetests;

import actions.Verifier;
import com.selenium.utils.Log;
import common.BaseTestClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.SiteSettingsPage;
import pageutils.NavigationUtil;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_EditUTM extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"site settings", "UTM"})
    public void editUtmTest(String testSite) {
        Verifier verifier = new Verifier();
        String newUTMsource = TestData.utm_source;
        String newUTMmedium = TestData.utm_medium;
        if (BetaFeatures.verifyBetaToTest("UTM")) {
            new LogInPage(driver).login(TestData.email, TestData.pass);
            SiteSettingsPage siteSettingsPage = new NavigationUtil(driver).open(SiteSettingsPage.class, testSite);

            String UTMsource = siteSettingsPage.getUtm_source();
            String UTMmedium = siteSettingsPage.getUtm_medium();
            siteSettingsPage.setNewUTM(newUTMsource, newUTMmedium);

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText(), newUTMsource);
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMmedium)).getText(), newUTMmedium);
            siteSettingsPage.setNewUTM(UTMsource, UTMmedium);

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText(), UTMsource);
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMmedium)).getText(), UTMmedium);
        } else {
            Log.info(this.getClass().getSimpleName() + "Current funtionality is not deployed on " + TestServerConfiguretion.iTest);
            throw new SkipException("Skipped");
        }
        verifier.assertTestPassed();
    }
}
