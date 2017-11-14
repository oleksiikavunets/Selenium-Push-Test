
import actions.UserActions;
import actions.Verifier;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import testutils.Listeners.LogListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

@Listeners(LogListener.class)
public class Test_Pos_Subscription extends SeleniumBaseClass {

    @Parameters("browser")
    @Test(groups = "subscription")
    public void testSubscription(@Optional("chrome") String browser) {
        Logger Log = LogManager.getLogger(Test_Pos_Subscription.class);

        Verifier verifier = new Verifier();
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        int totalAmountOfSubsBefore  = mainAdminPage.getAmountOfSubscribers();
        SideBar sideBar = mainAdminPage.openSite(TestData.testSite);
        SubscribersPage subscribersPage = sideBar.openSubscribersPage();

        int amountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        Log.info("Subs before: on main page - " + totalAmountOfSubsBefore +
        " on subs page - " + amountOfSubsBefore);
        headerMenu.logout();
        new UserActions(driver, wait).subscribe(browser, TestData.testSite);

        logInPage.login(TestData.email, TestData.pass);
        int totalAmountOfSubsAfter  = mainAdminPage.getAmountOfSubscribers();
        verifier.assertTrue(totalAmountOfSubsBefore < totalAmountOfSubsAfter, "Amount of subscribers did not change on main page. " +
                "Before: " + totalAmountOfSubsBefore +
        "After: " + totalAmountOfSubsAfter);
        mainAdminPage.openSite(TestData.testSite);
        sideBar.openSubscribersPage();
        int amountOfSubsAfter = subscribersPage.getAmountOfSubscribers();
        Log.info("Subs after: on main page - " + totalAmountOfSubsAfter +
                " on subs page - " + amountOfSubsAfter);

        verifier.assertTrue(amountOfSubsBefore < amountOfSubsAfter, "Amount of subscribers did not change on Subscribers page. " +
                "Before: " + amountOfSubsBefore +
                "After: " + amountOfSubsAfter);
        headerMenu.logout();
        verifier.assertTestPassed();
    }
}
