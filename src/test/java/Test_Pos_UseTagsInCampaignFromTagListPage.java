import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.List;

import static pageutils.TextGetter.textOf;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_UseTagsInCampaignFromTagListPage extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = {"send push", "tags", "tag list"})
    public void useTagsInNewCampaignFromTagListPage(@Optional("chrome") String browser) throws Exception {
        String title = TestData.pushTitle;
        String text = TestData.pushText;

        String[] tags = new String[]{
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(browser, TestData.testSite, tags);
        TagListPage tagListPage = new LogInPage(driver).login(TestData.email, TestData.pass)
                .openSite()
                .openTagListPage();
        tagListPage.addTagsToNewTL(tags);

        List<String> addedTags = textOf(driver.findElements(tagListPage.tagInNewTagList));

        CampaignReportPage campaignReportPage = tagListPage.useTLinNewCampaign()
                .setTitle(title)
                .setText(text)
                .sendPush()
                .openMessage(title);
        List<String> sentTags = textOf(campaignReportPage.getSentTags());
        System.out.println(sentTags);
        System.out.println(addedTags);
        Assert.assertTrue(sentTags.containsAll(addedTags), "Tags not found in sent campaign");
    }
}
