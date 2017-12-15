import actions.Verifier;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithBigImage extends BaseTestClass {


    @Test(groups = {"send push", "active elements", "big image"})
    public void sendMessageWithBIGImage() {

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
            LogInPage logInPage = new LogInPage(driver);
            new CreateCampaignPage(driver);
            Verifier verifier = new Verifier();

            CreateCampaignPage createCampaignPage = logInPage.login(TestData.email, TestData.pass)
                    .openSite().openCreateCampaignPage()
                    .setTitle(TestData.pushTitle).setText(TestData.pushText);

            CreateCampaignPage.AdditionalActiveItems bigImage = createCampaignPage.openAdditionalActiveItems();
            String image = bigImage.uploadBigImage(TestData.bigImage);
            verifier.assertTrue((createCampaignPage.bigImagePrev.findElement(driver)).isDisplayed());
            CampaignReportPage campaignReportPage = createCampaignPage.sendPush()
                    .openMessage(TestData.pushTitle);
            campaignReportPage.copyCampaign();

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.bigImagePrev)).getAttribute("src"), image);
            verifier.assertTestPassed();
        } else {
            throw new SkipException("");
        }

    }
}
