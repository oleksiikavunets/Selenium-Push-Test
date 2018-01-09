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
import testdata.TestData;
import testutils.Listeners.LogListener;

import java.util.List;

import static com.selenium.utils.TextGetter.textOf;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_SendMessageWithTags extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = {"send push", "advanced settings", "tags"})
    public void sendMessageWithTags(@Optional("chrome") String browser) throws Exception {
        LogInPage logInPage = new LogInPage(driver);
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String testSite = TestData.testSite;
        String[] newTags = new String[]{
                "t1" + RandomGenerator.nextString(),
                "t2" + RandomGenerator.nextString(),
                "t3" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(browser, testSite, newTags);

        CreateCampaignPage.AdvancedOptions advancedOptions = logInPage.login(TestData.email, TestData.pass)
                .openSite(testSite).openCreateCampaignPage()
                .setTitle(title).setText(text)
                .openAdvancedOptions()
                .addTagToCampaign(newTags);

        List<String> tagsInPush = textOf(advancedOptions.getTagsToBeSent());

        CampaignReportPage campaignReportPage = new CreateCampaignPage(driver).sendPush()
                .openMessage(title);
        List<String> sentTagsNames = textOf(campaignReportPage.getSentTags());

        Assert.assertTrue(sentTagsNames.containsAll(tagsInPush));
    }
}
