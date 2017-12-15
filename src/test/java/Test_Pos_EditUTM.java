import actions.Verifier;
import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import com.selenium.utils.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

@Listeners(LogListener.class)
public class Test_Pos_EditUTM extends BaseTestClass {

    @Test(groups = {"site settings", "UTM"})
    public void editUTM() throws InterruptedException {
        LogInPage logInPage = new LogInPage(driver);
        Verifier verifier = new Verifier();
        String newUTMsource = TestData.utm_source;
        String newUTMmedium = TestData.utm_medium;
        if (BetaFeatures.verifyBetaToTest("UTM")) {
            SiteSettingsPage siteSettingsPage = logInPage.login(TestData.email, TestData.pass)
                    .openSite().openSiteSettingsPage();

            String UTMsource = siteSettingsPage.getUtm_source();
            String UTMmedium = siteSettingsPage.getUtm_medium();
            siteSettingsPage.setNewUTM(newUTMsource, newUTMmedium);

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText(), newUTMsource);
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMmedium)).getText(), newUTMmedium);
            siteSettingsPage.setNewUTM(UTMsource, UTMmedium);

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMsource)).getText(), UTMsource);
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.UTMmedium)).getText(), UTMmedium);
        } else {
            Log.info(this.getClass().getSimpleName() + "Current funtionality is not deployed on " + ConfigTest.iTest);
            throw new SkipException("Skipped");
        }
        verifier.assertTestPassed();
    }
}
