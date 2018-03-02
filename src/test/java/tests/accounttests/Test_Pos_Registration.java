package tests.accounttests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import testconfigs.testdatamanagers.TestUserManager;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_Registration extends BaseTestClass {

    @Test(groups = {"mails", "registration"}, singleThreaded = true, threadPoolSize = 1)
    public void registrationTest() throws Exception {
        new UserActions(driver).createNewUser();
        new LogInPage(driver).login(new TestUserManager().getEmail(), new TestUserManager().getPassword());
    }
}

