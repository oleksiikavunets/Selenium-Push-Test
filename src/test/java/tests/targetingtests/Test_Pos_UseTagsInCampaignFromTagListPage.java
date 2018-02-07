package tests.targetingtests;

import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.List;

import static com.selenium.utils.TextGetter.textOf;
import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

@Listeners(LogListener.class)
public class Test_Pos_UseTagsInCampaignFromTagListPage extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider", groups = {"send push", "tags", "tag list"})
    public void useTagsInNewCampaignFromTagListPageTest(String testSite) throws Exception {
        String title = TestData.pushTitle;
        String text = TestData.pushText;

        String[] tags = new String[]{
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(testSite, tags);
        new LogInPage(driver).login(email, pass);
        TagListPage tagListPage = new Navigator(driver).open(TagListPage.class, testSite)
                .addTagsToNewTL(tags);

        List<String> addedTags = textOf(tagListPage.getAddedTags());

        CampaignReportPage campaignReportPage = tagListPage.useTLinNewCampaign()
                .setTitle(title)
                .setText(text)
                .sendPush()
                .openMessage(title);
        List<String> sentTags = textOf(campaignReportPage.getSentTags());
        System.out.println(sentTags);
        System.out.println(addedTags);
        Assert.assertTrue(sentTags.containsAll(addedTags), "Tags not found in sent campaign report");
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }
}
