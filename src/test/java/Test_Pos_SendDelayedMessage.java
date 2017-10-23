
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.time.LocalDateTime;


/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_SendDelayedMessage extends SeleniumBaseClass {


    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessage() throws Exception {
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

        createCampaignPage.setTitle(title);
        System.out.println(title);
        createCampaignPage.setText(text);
        createCampaignPage.setUrlToRedirect(siteUrl);
        createCampaignPage.setDateAndTime(year, month, day, hour, min); //int parameter plus days
        CampaignHistoryPage campaignHistoryPage =  createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
        Assert.assertTrue(campaignReportPage.verifyMessageDelayed());
        new HeaderMenu(driver, wait).logout();
    }
}
