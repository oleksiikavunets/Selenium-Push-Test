import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_DeleteDelayedMessage extends BaseTestClass {

    @Test(groups = {"send push", "delayed push"})
    public void deleteDelayedMessage() throws AWTException {
        Logger Log = LogManager.getLogger(Test_Pos_DeleteDelayedMessage.class);

        LogInPage logInPage = new LogInPage(driver);
        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        CampaignReportPage campaignReportPage = logInPage.login(TestData.email, TestData.pass)
                .openSite(testSite).openCreateCampaignPage()
                .setTitle(title).setText(text)
                .setDateAndTime(10, 0, 0)
                .sendPush().openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        CampaignHistoryPage campaignHistoryPage = campaignReportPage.cancelCampaign();

        Assert.assertFalse(campaignHistoryPage.verifyMessageExists(title), "Message was not deleted");
    }
}
