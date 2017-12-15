import actions.UserActions;
import com.selenium.ConfigTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import testutils.Listeners.LogListener;

/**
 * Created by Rayant on 11.04.2017.
 */
@Listeners(LogListener.class)
public class Test_Pos_Registration extends BaseTestClass {

    @Test(groups = {"mails", "registration"}, singleThreaded = true, threadPoolSize = 1)
    public void registration() throws Exception {

        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber());
        new UserActions(driver).createNewUser();
        String pass = config.getPassword();
        new LogInPage(driver).login("grovitek+" + emailNumber + "@gmail.com", pass);
    }
}

