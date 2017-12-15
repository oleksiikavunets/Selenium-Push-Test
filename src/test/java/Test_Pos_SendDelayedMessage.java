
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.LogInPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.time.LocalDateTime;


/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendDelayedMessage extends BaseTestClass {


    @Test(groups = {"send push", "delayed push"})
    public void sendDelayedMessage() throws Exception {
        LocalDateTime date = LocalDateTime.now().plusDays(10);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int min = 0;

        String testSite = TestData.testSite;
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";

        CampaignReportPage campaignReportPage = new LogInPage(driver).login(TestData.email, TestData.pass)
                .openSite(testSite).openCreateCampaignPage()
                .setTitle(title).setText(text).setUrlToRedirect(siteUrl)
                .setDateAndTime(year, month, day, hour, min) //int parameter plus days
                .sendPush().openMessage(title);

        Assert.assertTrue(campaignReportPage.verifyMessageDelayed());
    }
}
