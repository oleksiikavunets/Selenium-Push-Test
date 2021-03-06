package tests.accounttests;

import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testconfigs.testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_LogInLogOut extends BaseTestClass {

    @Test
    public void logInOutTest()  {

        new LogInPage(driver).login(TestData.email, TestData.pass);
        new HeaderMenu(driver).logout();
    }
}
