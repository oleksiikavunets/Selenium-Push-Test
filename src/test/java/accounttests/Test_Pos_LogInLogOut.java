package accounttests;

import common.BaseTestClass;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_LogInLogOut extends BaseTestClass {

    @Test
    public void logInOutTest() throws Exception {

        System.out.println(driver instanceof OperaDriver);
        new LogInPage(driver).login(TestData.email, TestData.pass);
        new HeaderMenu(driver).logout();
    }
}
