package tests.usersitetests;

import actions.Verifier;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import common.BaseTestClass;
import pageutils.Navigator;
import testutils.Listeners.LogListener;
import com.selenium.utils.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testconfigs.testdata.TestData;
import testconfigs.testrestrictions.BetaFeatures;

import static testconfigs.testdata.TestData.testSite;

@Listeners(LogListener.class)
public class Test_Pos_EditUTM extends BaseTestClass {

    @Test(groups = {"site settings", "UTM"})
    public void editUTMTest() throws InterruptedException {
        Verifier verifier = new Verifier();
        String newUTMsource = TestData.utm_source;
        String newUTMmedium = TestData.utm_medium;
        if (BetaFeatures.verifyBetaToTest("UTM")) {
            new LogInPage(driver).login(TestData.email, TestData.pass);
            SiteSettingsPage siteSettingsPage = new Navigator(driver).open(SiteSettingsPage.class, testSite);

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
