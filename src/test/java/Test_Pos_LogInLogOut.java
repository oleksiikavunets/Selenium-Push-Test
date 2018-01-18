import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_LogInLogOut extends BaseTestClass {

    @Test
    public void logInOut() throws Exception {
        try {
            new LogInPage(driver).login(TestData.email, TestData.pass);
       //     new UserActions(driver).deleteUnnecessarySites();
            new HeaderMenu(driver).logout();
        } catch(AssertionError e){
            e.printStackTrace();
            logInOut();
        } catch(WebDriverException e){
            e.printStackTrace();
            logInOut();
        }
    }
}
