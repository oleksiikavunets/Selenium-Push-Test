package statistictests;

import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.Navigator;
import testdata.TestData;

import static testdata.TestData.*;

public class Test_Pos_SubscriberStatistics extends BaseTestClass {

    @Test(dataProvider = "testSiteProvider")
    public void allSubscribersAmountTest(String testSite) {

        Navigator navigator = new Navigator(driver);

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        int amountOfSiteSubscribers = mainAdminPage.getAmountOfSiteSubscribers(testSite);
        System.out.println(amountOfSiteSubscribers);
        SubscribersPage subscribersPage = navigator.open(SubscribersPage.class, testSite);
        int amountOfAllSubscribers = subscribersPage.getAmountOfSubscribers();
        Assert.assertEquals(amountOfSiteSubscribers, amountOfAllSubscribers);
        int amountOfAllSubs = subscribersPage.switchLifeTimeStats().getAmountOfSubscribers();
        int amountOfUnsbSubs = subscribersPage.getAmountOfUnsbSubs();
        int amounOfNewSubs = subscribersPage.getAmountOfNewSubs();
        Assert.assertEquals(amountOfAllSubs + amountOfUnsbSubs, amounOfNewSubs);
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestData.provideTestSites();
    }

}
