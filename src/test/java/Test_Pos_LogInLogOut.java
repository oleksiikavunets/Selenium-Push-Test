import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import testdata.TestData;

import java.time.LocalDateTime;

/**
 * Created by Oleksii on 31.07.2017.
 */
public class Test_Pos_LogInLogOut extends SeleniumBaseClass {

    @Test
    public void logInOut() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);


        LocalDateTime date = LocalDateTime.now();
        System.out.println(date.toString());
        new LogInPage(driver, wait).login(TestData.email, TestData.pass);
//        Cookie token = driver.manage().getCookieNamed("accessToken");
//        Set<Cookie> cookies = driver.manage().getCookies();
//        System.out.println(token);
//        System.out.println(cookies);
        headerMenu.logout();
    }
}