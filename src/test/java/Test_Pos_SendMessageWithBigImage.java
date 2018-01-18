import actions.Verifier;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

import static testdata.TestData.testSite;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithBigImage extends BaseTestClass {


    @Test(groups = {"send push", "active elements", "big image"})
    public void sendMessageWithBIGImage() {

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            new CreateCampaignPage(driver);
            Verifier verifier = new Verifier();

            new LogInPage(driver).login(TestData.email, TestData.pass);
            CreateCampaignPage createCampaignPage = new Navigator(driver).open(CreateCampaignPage.class, testSite)

                    .setTitle(TestData.pushTitle)
                    .setText(TestData.pushText);

            CreateCampaignPage.AdditionalActiveItems bigImage = createCampaignPage.openAdditionalActiveItems();
            String image = bigImage.uploadBigImage(TestData.bigImage);
            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();
            verifier.assertTrue(notificationPreview.getBigImgPreview().isDisplayed());
            CampaignReportPage campaignReportPage = createCampaignPage.sendPush()
                    .openMessage(TestData.pushTitle);
            campaignReportPage.copyCampaign();

            verifier.assertEquals(notificationPreview.getBigImagePreview().getAttribute("src"), image);
            verifier.assertTestPassed();
        } else {
            throw new SkipException("");
        }

    }
}
