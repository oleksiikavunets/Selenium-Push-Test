import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import pageobjects.WelcomeMessagePage;
import pageutils.Navigator;

import static actions.Timer.waitSeconds;
import static testdata.TestData.email;
import static testdata.TestData.pass;
import static testdatamanagers.TestSiteManager.getTestSiteUrl;

public class NavigationTest extends BaseTestClass {

    @Test
    public void testNav(){
        Navigator navigator = new Navigator(driver);
        new LogInPage(driver).login(email, pass);
        navigator.open(WelcomeMessagePage.class, getTestSiteUrl());
        navigator.open(TagListPage.class, getTestSiteUrl());
        CreateCampaignPage createCampaignPage =  navigator.open(CreateCampaignPage.class, getTestSiteUrl());
        createCampaignPage.setTitle("QWERTYUIOPOIUYTREW");
        waitSeconds(30);
    }
}
