
import actions.UserActions;
import actions.Verifier;

import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

public class Test_Pos_Subscription extends SeleniumBaseClass {

    @Test(groups = "subscription")
    public void testSubscription() {
        Verifier verifier = new Verifier();
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);

        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        int totalAmountOfSubsBefore  = mainAdminPage.getAmountOfSubscribers();
        SideBar sideBar = mainAdminPage.openSite(TestData.testSite);
        SubscribersPage subscribersPage = sideBar.openSubscribersPage();

        int amountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs before: on main page - " + totalAmountOfSubsBefore +
        " on subs page - " + amountOfSubsBefore);
        headerMenu.logout();
        new UserActions(driver, wait).subscribe(TestData.testSite);

        logInPage.login(TestData.email, TestData.pass);
        int totalAmountOfSubsAfter  = mainAdminPage.getAmountOfSubscribers();
        verifier.assertTrue(totalAmountOfSubsBefore < totalAmountOfSubsAfter, "Amount of subscribers did not change on main page. " +
                "Before: " + totalAmountOfSubsBefore +
        "After: " + totalAmountOfSubsAfter);
        mainAdminPage.openSite(TestData.testSite);
        sideBar.openSubscribersPage();
        int amountOfSubsAfter = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs after: on main page - " + amountOfSubsAfter +
                " on subs page - " + amountOfSubsAfter);

        verifier.assertTrue(amountOfSubsBefore < amountOfSubsAfter, "Amount of subscribers did not change on Subscribers page. " +
                "Before: " + amountOfSubsBefore +
                "After: " + amountOfSubsAfter);
        headerMenu.logout();
        verifier.assertTestPassed();
    }
}
