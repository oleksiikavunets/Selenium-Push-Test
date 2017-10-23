import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.awt.*;

public class Test_Pos_DeleteDelayedMessage extends SeleniumBaseClass{

    @Test(groups = {"send push", "delayed push"})
    public void deleteDelayedMessage() throws AWTException {

        LogInPage logInPage = new LogInPage(driver, wait);

        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

        createCampaignPage.setTitle(title);
        System.out.println(title);
        createCampaignPage.setText(text);
        createCampaignPage.setDateAndTime(10, 0, 0);
        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.cancelCampaign();
        Assert.assertFalse(campaignHistoryPage.verifyMessageExists(title), "Message was not deleted");
        new HeaderMenu(driver, wait).logout();
    }
}
