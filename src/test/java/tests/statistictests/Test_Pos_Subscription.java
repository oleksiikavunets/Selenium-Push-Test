package tests.statistictests;

import actions.UserActions;
import actions.Verifier;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static testconfigs.testdatamanagers.TestSiteManager.getSiteOwner;

@Listeners(LogListener.class)
public class Test_Pos_Subscription extends BaseTestClass {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getNewlyCreatedSites", groups = "subscription")
    public void subscriptionTest(String testSite) {

        NavigationUtil navigationUtil = new NavigationUtil(driver);

        Verifier verifier = new Verifier();
        LogInPage logInPage = new LogInPage(driver);

        String[] siteOwner = getSiteOwner(testSite);
        String email = siteOwner[0];
        String pass = siteOwner[1];

        MainAdminPage mainAdminPage = logInPage.login(email, pass);
        int totalAmountOfSubsBefore = mainAdminPage.getTotalAmountOfSubscribers();
        SubscribersPage subscribersPage = navigationUtil.open(SubscribersPage.class, testSite);
//                .clickTodayBtn();

        int amountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs before: on main page - " + totalAmountOfSubsBefore +
                " on subs page - " + amountOfSubsBefore);
        new HeaderMenu(driver).logout();
        new UserActions(driver, wait).subscribe(testSite);

        logInPage.login(email, pass);
        int totalAmountOfSubsAfter = mainAdminPage.getTotalAmountOfSubscribers();
        verifier.assertTrue(totalAmountOfSubsBefore < totalAmountOfSubsAfter, "Amount of subscribers did not change on main page. " +
                "Before: " + totalAmountOfSubsBefore +
                " After: " + totalAmountOfSubsAfter);

        navigationUtil.open(SubscribersPage.class, testSite);//.clickTodayBtn();

        int amountOfSubsAfter = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs after: on main page - " + totalAmountOfSubsAfter +
                " on subs page - " + amountOfSubsAfter);

        verifier.assertTrue(amountOfSubsBefore < amountOfSubsAfter, "Amount of subscribers did not change on Subscribers page. " +
                "Before: " + amountOfSubsBefore +
                " After: " + amountOfSubsAfter);
        verifier.assertTestPassed();
    }
}
