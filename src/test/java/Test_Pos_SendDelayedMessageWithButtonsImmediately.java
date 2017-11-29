import actions.Timer;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

public class Test_Pos_SendDelayedMessageWithButtonsImmediately extends SeleniumBaseClass{

    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessageWithButtonsImmediately(){
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
        CreateCampaignPage.AdditionalActiveItems buttons = createCampaignPage.openAdditionalActiveItems();

        buttons.setButtons("Button1", "https://push.gravitec.net:7700/b1", "Button2", "https://push.gravitec.net:7700/b2");
        buttons.setButton1Icon();
        buttons.setButton2Icon();
        Timer.waitSeconds(1);

        CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        campaignReportPage.verifyMessageDelayed();
        campaignReportPage.clickEditCampaign();
        createCampaignPage.cancelDate();
        createCampaignPage.clickSendPush();
        Assert.assertFalse(campaignReportPage.verifyMessageDelayed(), "Message was not sent");






    }


}
