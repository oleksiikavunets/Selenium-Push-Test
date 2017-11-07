import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessageImmediately extends SeleniumBaseClass{

    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessageImmediately() throws AWTException {

        LogInPage logInPage = new LogInPage(driver, wait);

        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);
        createCampaignPage.setDateAndTime(10, 0, 0);
        CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign();
        createCampaignPage.cancelDate();
        createCampaignPage.clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent");
        new HeaderMenu(driver, wait).logout();
    }
}
