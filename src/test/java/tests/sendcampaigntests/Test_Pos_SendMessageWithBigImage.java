package tests.sendcampaigntests;

import actions.Verifier;
import common.BaseTestClass;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithBigImage extends BaseTestClass {


    @Test(dataProvider = "testSiteProvider", groups = {"send push", "active elements", "big image"})
    public void sendMessageWithBIGImageTest(String testSiteUrl) {

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            new CreateCampaignPage(driver);
            Verifier verifier = new Verifier();

            new LogInPage(driver).login(TestData.email, TestData.pass);
            CreateCampaignPage createCampaignPage = new Navigator(driver).open(CreateCampaignPage.class, testSiteUrl)

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

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }

}
