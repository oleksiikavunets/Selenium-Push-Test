package tests.sendcampaigntests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CampaignReportPage;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestDataProvider;
import utils.RandomGenerator;

import java.util.Arrays;

import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;
import static utils.TextUtil.textOf;

public class Test_Pos_CopyCampaignWithAdvancedTargeting extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"send push", "copy campaign", "advanced settings", "tags", "alias", "active elements", "buttons", "big image"})
    public void copyCampaignWithAdvancedTargetingTest(String testSite) throws Exception {

        String title = "PUSH TITLE: " + RandomGenerator.nextString();
        String text = "PUSH WITH TAGS AND ALIAS TO COPY";
        String newTitle = title + "new";
        String[] newTags = new String[]{
                "t1" + RandomGenerator.nextString(),
                "t2" + RandomGenerator.nextString(),
                "t3" + RandomGenerator.nextString()};

        String newAlias = "alias" + RandomGenerator.nextString();
        UserActions userActions = new UserActions(driver);
        NavigationUtil navigationUtil = new NavigationUtil(driver);
        userActions.addNewAlias(testSite, newAlias);
        userActions.addNewTag(testSite, newTags);

        new LogInPage(driver).login(email, pass);
        CreateCampaignPage createCampaignPage = navigationUtil.open(CreateCampaignPage.class, testSite)
                .setTitle(title)
                .setText(text);
        createCampaignPage.openAdvancedOptions()
                .addTagToCampaign(newTags)
                .addAliasToCampaign(newAlias);

        CampaignReportPage campaignReportPage = createCampaignPage.sendPush()
                .openMessage(title)
                .copyCampaign()
                .setTitle("new")
                .sendPush().openMessage(newTitle);
        Assert.assertTrue(textOf(campaignReportPage.getSentTags()).containsAll(Arrays.asList(newTags)));
        Assert.assertTrue(campaignReportPage.getSentAlias().getText().equals(newAlias));


    }
}
