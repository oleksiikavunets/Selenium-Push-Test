import actions.UserActions;
import com.selenium.ConfigTest;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;

/**
 * Created by Rayant on 11.04.2017.
 */
public class Test_Pos_Registration extends SeleniumBaseClass {

    @Test(groups = {"mails", "registration"})
    public void registration() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        ConfigTest config = new ConfigTest();
        int emailNumber = Integer.valueOf(config.getEmailNumber());
        userActions.createNewUser();

        String pass = config.getPassword();
        new LogInPage(driver, wait).login("grovitek+" + emailNumber + "@gmail.com", pass);
        headerMenu.logout();
    }
}

