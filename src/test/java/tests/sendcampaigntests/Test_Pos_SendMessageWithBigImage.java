package tests.sendcampaigntests;

import actions.Verifier;
import utils.RandomGenerator;
import common.BaseTestClass;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testconfigs.testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

import java.util.Optional;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithBigImage extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "active elements", "big image"})
    public void sendMessageWithBigImageTest(String testSiteUrl) {

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {

            String pushTitle = "PUSH TITLE: " + RandomGenerator.nextString();
            String pushText = "THERE MUST BE BIG IMAGE BELOW";
            new CreateCampaignPage(driver);
            Verifier verifier = new Verifier();

            new LogInPage(driver).login(TestData.email, TestData.pass);
            CreateCampaignPage createCampaignPage = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                    .setTitle(pushTitle)
                    .setText(pushText);

            CreateCampaignPage.AdditionalActiveItems bigImage = createCampaignPage.openAdditionalActiveItems();
            String image = bigImage.uploadBigImage(TestData.bigImage);
            CreateCampaignPage.NotificationPreview notificationPreview = createCampaignPage.new NotificationPreview();
            verifier.assertTrue(
                    Optional.ofNullable(notificationPreview.getBigImagePreview())
                            .orElseThrow(() -> new NoSuchElementException("BIG IMAGE NOT DISPLAYED IN NOTIFICATION PREVIEW"))
                            .isDisplayed());
            createCampaignPage.sendPush()
                    .openMessage(pushTitle)
                    .copyCampaign();

            verifier.assertEquals(notificationPreview.getBigImagePreview().getAttribute("src"), image);
            verifier.assertTestPassed();
        } else {
            throw new SkipException("");
        }
    }

}
