import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.Navigator;
import testdata.TestData;
import testutils.Listeners.LogListener;

import static testdatamanagers.TestDataManager.getAlias;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithAlias extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = {"send push", "advanced settings", "alias"})
    public void sendMessageWithAlias(@Optional("chrome") String browser) throws Exception {

        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String alias = getAlias();
        String testSite = TestData.testSite;

        new UserActions(driver).addNewAlias(browser, testSite, alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = new Navigator(driver).open(CreateCampaignPage.class, testSite)
                .setTitle(title)
                .setText(text)
                .openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush().openMessage(title);
        Assert.assertEquals(alias, campaignReportPage.getSentAlias().getText());
    }
}
