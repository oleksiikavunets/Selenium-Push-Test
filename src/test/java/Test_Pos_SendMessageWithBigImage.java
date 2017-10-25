import actions.Verifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;
import testrestrictions.BetaFeatures;

/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_SendMessageWithBigImage extends SeleniumBaseClass {


    @Test(groups = {"send push", "active elements", "big image"})
    public void sendMessageWithBIGImage() {
        Logger Log = LogManager.getLogger(Test_Pos_SendMessageWithBigImage.class);

        if (BetaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
            LogInPage logInPage = new LogInPage(driver, wait);
            CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver, wait);
            Verifier verifier = new Verifier();

            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            SideBar sideBar = mainAdminPage.openSite();

            sideBar.openCreateCampaignPage();
            createCampaignPage.setTitle(TestData.pushTitle);
            createCampaignPage.setText(TestData.pushText);
            CreateCampaignPage.AdditionalActiveItems bigImage = createCampaignPage.openAdditionalActiveItems();
            String image = bigImage.uploadBigImage(TestData.bigImage);
            verifier.assertTrue((createCampaignPage.bigImagePrev.findElement(driver)).isDisplayed());
            CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();
            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(TestData.pushTitle);
            campaignReportPage.copyCampaign();

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.bigImagePrev)).getAttribute("src"), image);
            new HeaderMenu(driver, wait).logout();
            verifier.assertTestPassed();
        } else {
            Log.info("Test: COPY CAMPAIGN> " +
                    "Current functionality is not deployed on kyivstar yet");
        }

    }
}
