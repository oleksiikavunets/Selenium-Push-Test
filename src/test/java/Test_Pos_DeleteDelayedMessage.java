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
public class Test_Pos_DeleteDelayedMessage extends SeleniumBaseClass{

    @Test(groups = {"send push", "delayed push"})
    public void deleteDelayedMessage() throws AWTException {
        Logger Log = LogManager.getLogger(Test_Pos_DeleteDelayedMessage.class);

        LogInPage logInPage = new LogInPage(driver, wait);

        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);
        Log.info("DELAYED PUSH DATA> TITLE: " + title + "; TEXT: " + text);
        createCampaignPage.setDateAndTime(10, 0, 0);
        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.cancelCampaign();
        Assert.assertFalse(campaignHistoryPage.verifyMessageExists(title), "Message was not deleted");
        new HeaderMenu(driver, wait).logout();
    }
}
