import com.selenium.utils.Listener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(Listener.class)
public class Test_Pos_UseTagsInCampaignFromTagListPage extends SeleniumBaseClass {

    @Test(groups = {"send push", "tags", "tag list"})
    public void useTagsInNewCampaignFromTagListPage() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        String title = TestData.pushTitle;
        String text = TestData.pushText;

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite();
        TagListPage tagListPage = sideBar.openTagListPage();
        tagListPage.addTagsToNewTL(3);

        List<WebElement> tags = driver.findElements(tagListPage.tagInNewTagList);
        List<String> tagsNames = new ArrayList<>();
        for (WebElement tag : tags) {
            tagsNames.add(tag.getText());
        }

        CreateCampaignPage createCampaignPage = tagListPage.useTLinNewCampaign();
        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);
        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();
        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);

        wait.until(ExpectedConditions.visibilityOfElementLocated(campaignReportPage.tag));
        List<WebElement> sentTags = driver.findElements(campaignReportPage.tag);
        List<String> sentTagsNames = new ArrayList<>();
        for (WebElement sentTag : sentTags) {
            sentTagsNames.add(sentTag.getText());
        }
        System.out.println(sentTagsNames);
        System.out.println(tagsNames);
        Assert.assertTrue(sentTagsNames.containsAll(tagsNames));
        new HeaderMenu(driver, wait).logout();
    }
}
