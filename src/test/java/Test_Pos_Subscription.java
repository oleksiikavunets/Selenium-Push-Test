
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
        int amountOfSubsBefore = mainAdminPage.getAmountOfSubscribers();
        SideBar sideBar = mainAdminPage.openSite(TestData.testSite);
        SubscribersPage subscribersPage = sideBar.openSubscribersPage();

        int totalAmountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs before: on main page - " + amountOfSubsBefore +
        " on subs page - " + totalAmountOfSubsBefore);
        headerMenu.logout();
        new UserActions(driver, wait).subscribe(TestData.testSite);

        logInPage.login(TestData.email, TestData.pass);
        int amountOfSubsAfter = mainAdminPage.getAmountOfSubscribers();
        verifier.assertTrue(amountOfSubsBefore < amountOfSubsAfter);
        mainAdminPage.openSite(TestData.testSite);
        sideBar.openSubscribersPage();
        int totalAmountOfSubsAfter = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs after: on main page - " + amountOfSubsAfter +
                " on subs page - " + totalAmountOfSubsAfter);

        verifier.assertTrue(totalAmountOfSubsBefore < totalAmountOfSubsAfter);
        headerMenu.logout();
        verifier.assertTestPassed();
    }
}
