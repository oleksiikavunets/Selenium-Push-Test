import testutils.Listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testdata.TestData;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_LogInLogOut extends SeleniumBaseClass {

    @Test
    public void logInOut() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);

        try {
            new LogInPage(driver, wait).login(TestData.email, TestData.pass);

            headerMenu.logout();
        } catch(org.openqa.selenium.TimeoutException timeOut){
            logInOut();
        }
    }
}
