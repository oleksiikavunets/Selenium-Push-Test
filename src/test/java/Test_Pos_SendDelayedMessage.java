
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.time.LocalDateTime;


/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessage extends SeleniumBaseClass {


    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessage() throws Exception {
        Logger Log = LogManager.getLogger(Test_Pos_SendDelayedMessage.class);
        LocalDateTime date = LocalDateTime.now().plusDays(10);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int min = 0;
        LogInPage logInPage = new LogInPage(driver, wait);

        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
//        Log.info("DELAYED PUSH DATA> TITLE: " + title + "; TEXT: " + text + "; REDIRECT URL: " + siteUrl + "; DATE: " + date);

        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);
        createCampaignPage.setUrlToRedirect(siteUrl);
        createCampaignPage.setDateAndTime(year, month, day, hour, min); //int parameter plus days
        CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        Assert.assertTrue(campaignReportPage.verifyMessageDelayed());
        new HeaderMenu(driver, wait).logout();
    }
}
