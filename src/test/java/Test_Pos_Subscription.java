
import actions.UserActions;
import actions.Verifier;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.Navigator;
import testutils.Listeners.LogListener;

import static testdatamanagers.TestSiteManager.getHttpSiteUrl;
import static testdatamanagers.TestUserManager.getEmail;
import static testdatamanagers.TestUserManager.getPassword;

@Listeners(LogListener.class)
public class Test_Pos_Subscription extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = "subscription")
    public void testSubscription(@Optional("chrome") String browser) {

        Navigator navigator = new Navigator(driver);

        Verifier verifier = new Verifier();
        LogInPage logInPage = new LogInPage(driver);

        String email = getEmail();
        String pass = getPassword();
        String httpSite = getHttpSiteUrl();

        MainAdminPage mainAdminPage = logInPage.login(email, pass);
        int totalAmountOfSubsBefore = mainAdminPage.getTotalAmountOfSubscribers();
        SubscribersPage subscribersPage = navigator.open(SubscribersPage.class, httpSite);
//                .clickTodayBtn();

        int amountOfSubsBefore = subscribersPage.getAmountOfSubscribers();
        System.out.println("Subs before: on main page - " + totalAmountOfSubsBefore +
                " on subs page - " + amountOfSubsBefore);
        new HeaderMenu(driver).logout();
        new UserActions(driver, wait).subscribe(browser, httpSite);

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
}
