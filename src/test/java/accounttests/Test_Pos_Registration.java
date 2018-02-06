package accounttests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import testutils.Listeners.LogListener;

import static testdatamanagers.TestUserManager.getEmail;
import static testdatamanagers.TestUserManager.getPassword;

/**
 * Created by Rayant on 11.04.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_Registration extends BaseTestClass {

    @Test(groups = {"mails", "registration"}, singleThreaded = true, threadPoolSize = 1)
    public void registrationTest() throws Exception {
        new UserActions(driver).createNewUser();
        new LogInPage(driver).login(getEmail(), getPassword());
    }
}

