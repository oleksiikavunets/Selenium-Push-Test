import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;

import static testdata.TestData.email;
import static testdata.TestData.pass;
import static testdatamanagers.TestSiteManager.getTestSiteUrl;

public class Test_Pos_SubscriberStatistics extends BaseTestClass{

    @Test
    public void allSubscribersAmountTest() {

        String testSite = getTestSiteUrl();

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        int amountOfSiteSubscribers = mainAdminPage.getAmountOfSiteSubscribers(testSite);
        System.out.println(amountOfSiteSubscribers);
        SubscribersPage subscribersPage = mainAdminPage.openSite(testSite).openSubscribersPage();
        int amountOfAllSubscribers = subscribersPage.getAmountOfSubscribers();
        Assert.assertEquals(amountOfSiteSubscribers, amountOfAllSubscribers);
        int amountOfAllSubs = subscribersPage.switchLifeTimeStats().getAmountOfSubscribers();
        int amountOfUnsbSubs = subscribersPage.getAmountOfUnsbSubs();
        int amounOfNewSubs = subscribersPage.getAmountOfNewSubs();
        Assert.assertEquals(amountOfAllSubs + amountOfUnsbSubs, amounOfNewSubs);
    }
}
