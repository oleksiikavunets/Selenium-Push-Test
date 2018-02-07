package tests.statistictests;

import actions.UserActions;
import actions.Verifier;
import com.selenium.enums.Protocol;
import common.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.Navigator;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static testconfigs.testdatamanagers.TestSiteManager.getHttpSiteOwner;
import static testconfigs.testdatamanagers.TestSiteManager.getNewTestSiteUrl;

@Listeners(LogListener.class)
public class Test_Pos_Subscription extends BaseTestClass {


    @Test(dataProvider = "testSiteProvider", groups = "subscription")
    public void subscriptionTest(String testSite) {

        Navigator navigator = new Navigator(driver);

        Verifier verifier = new Verifier();
        LogInPage logInPage = new LogInPage(driver);

        String[] siteOwner = getHttpSiteOwner();
        String email = siteOwner[0];
        String pass = siteOwner[1];
        String httpSite = getNewTestSiteUrl(Protocol.HTTP);


        MainAdminPage mainAdminPage = logInPage.login(email, pass);
        int totalAmountOfSubsBefore = mainAdminPage.getTotalAmountOfSubscribers();
        SubscribersPage subscribersPage = navigator.open(SubscribersPage.class, httpSite);
//                .clickTodayBtn();

        int amountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs before: on main page - " + totalAmountOfSubsBefore +
                " on subs page - " + amountOfSubsBefore);
        new HeaderMenu(driver).logout();
        new UserActions(driver, wait).subscribe(httpSite);

        logInPage.login(email, pass);
        int totalAmountOfSubsAfter = mainAdminPage.getTotalAmountOfSubscribers();
        verifier.assertTrue(totalAmountOfSubsBefore < totalAmountOfSubsAfter, "Amount of subscribers did not change on main page. " +
                "Before: " + totalAmountOfSubsBefore +
                " After: " + totalAmountOfSubsAfter);

        navigator.open(SubscribersPage.class, httpSite);//.clickTodayBtn();

        int amountOfSubsAfter = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs after: on main page - " + totalAmountOfSubsAfter +
                " on subs page - " + amountOfSubsAfter);

        verifier.assertTrue(amountOfSubsBefore < amountOfSubsAfter, "Amount of subscribers did not change on Subscribers page. " +
                "Before: " + amountOfSubsBefore +
                " After: " + amountOfSubsAfter);
        verifier.assertTestPassed();
    }

    @DataProvider(name = "testSiteProvider")
    public Object[] provideTestSites() {
        return TestDataProvider.provideTestSites();
    }

}
