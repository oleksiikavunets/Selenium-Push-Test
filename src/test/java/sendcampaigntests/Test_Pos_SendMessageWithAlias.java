package sendcampaigntests;

import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.*;
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


    @Test(dataProvider = "testSiteProvider", groups = {"send push", "advanced settings", "alias"})
    public void sendMessageWithAliasTest(String testSiteUrl) throws Exception {

        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String alias = getAlias();

        new UserActions(driver).addNewAlias(testSiteUrl, alias);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = new Navigator(driver).open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .openAdvancedOptions();
        advancedOptions.addAliasToCampaign(alias);

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush().openMessage(title);
        Assert.assertEquals(alias, campaignReportPage.getSentAlias().getText());
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }
}
