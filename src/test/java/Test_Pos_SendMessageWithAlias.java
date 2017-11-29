import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithAlias extends SeleniumBaseClass {

    @Test(groups = {"send push", "advanced settings", "alias"})
    public void sendMessageWithAlias() throws Exception {
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String alias = TestData.alias;
        String testSite = TestData.testSite;

        MainAdminPage mainAdminPage = new LogInPage(driver, wait).login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);

        CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);

        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);

        Assert.assertEquals(alias,
                wait.until(ExpectedConditions.visibilityOfElementLocated(campaignReportPage.alias)).getText());
    }
}
