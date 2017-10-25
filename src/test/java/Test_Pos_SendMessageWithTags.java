import com.selenium.utils.Listener;
import com.selenium.utils.RandomGenerator;
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
public class Test_Pos_SendMessageWithTags extends SeleniumBaseClass {

    @Test(groups = {"send push", "advanced settings", "tags"})
    public void sendMessageWithTags() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);

        List<String> tagsInPush = new ArrayList<>();
        List<String> sentTagsNames = new ArrayList<>();
        String title = RandomGenerator.nextString();
        String text = RandomGenerator.nextString();
        String tag = TestData.tag;
        String testSite = TestData.testSite;

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);

        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
        createCampaignPage.setTitle(title);
        createCampaignPage.setText(text);

        CreateCampaignPage.AdvancedOptions advancedOptions = createCampaignPage.openAdvancedOptions();
        advancedOptions.addTagToCampaign(tag);

        List<WebElement> allTags = driver.findElements(createCampaignPage.tagsToSend);
        for (WebElement tagElement : allTags) {
            tagsInPush.add(tagElement.getText());
        }
        for (String s : tagsInPush) {
            System.out.println(s);
        }
        CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

        CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);

        wait.until(ExpectedConditions.visibilityOfElementLocated(campaignReportPage.tag));
        List<WebElement> sentTags = driver.findElements(campaignReportPage.tag);
        for (WebElement t : sentTags) {
            sentTagsNames.add(t.getText());
        }
        new HeaderMenu(driver, wait).logout();
        Assert.assertTrue(sentTagsNames.containsAll(tagsInPush));
    }
}
