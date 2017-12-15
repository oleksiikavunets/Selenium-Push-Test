import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessageImmediately extends BaseTestClass {

    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessageImmediately() throws AWTException {

        LogInPage logInPage = new LogInPage(driver);
        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        CampaignReportPage campaignReportPage = logInPage.login(TestData.email, TestData.pass)
                .openSite(testSite).openCreateCampaignPage()
                .setTitle(title).setText(text)
                .setDateAndTime(10, 0, 0)
                .sendPush()
                .openMessage(title);
        Assert.assertTrue(campaignReportPage.verifyMessageDelayed(), "Message is not delayed");
        campaignReportPage.clickEditCampaign()
                .cancelDate().clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent immediately");
    }
}
