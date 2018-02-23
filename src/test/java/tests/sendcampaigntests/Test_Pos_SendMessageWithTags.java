package tests.sendcampaigntests;

import actions.UserActions;
import utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import java.util.List;

import static utils.TextUtil.textOf;

@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithTags extends BaseTestClass {

    @Parameters("browser")
    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "advanced settings", "tags"})
    public void sendMessageWithTagsTest(String testSiteUrl) throws Exception {

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "TAGS";
        String[] newTags = new String[]{
                "t1" + RandomGenerator.nextString(),
                "t2" + RandomGenerator.nextString(),
                "t3" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(testSiteUrl, newTags);

        new LogInPage(driver).login(TestData.email, TestData.pass);
        CreateCampaignPage.AdvancedOptions advancedOptions = new NavigationUtil(driver).open(CreateCampaignPage.class, testSiteUrl)
                .setTitle(title)
                .setText(text)
                .openAdvancedOptions()
                .addTagToCampaign(newTags);
        System.out.println("title: " + title);

        List<String> tagsInPush = textOf(advancedOptions.getTagsToBeSent());

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush()
                .openMessage(title);
        List<String> sentTagsNames = textOf(campaignReportPage.getSentTags());

        Assert.assertTrue(sentTagsNames.containsAll(tagsInPush));
    }
}
