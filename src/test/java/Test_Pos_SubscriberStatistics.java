import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.Navigator;

import static testdata.TestData.*;

public class Test_Pos_SubscriberStatistics extends BaseTestClass{

    @Test
    public void allSubscribersAmountTest() {

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
}
