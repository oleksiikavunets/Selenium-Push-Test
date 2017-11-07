import actions.UserActions;
import testutils.Listeners.LogListener;
import com.selenium.utils.RandomGenerator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.SiteSettingsPage;
import testdata.TestData;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_ChangeSiteIcon extends SeleniumBaseClass {


    @Test (groups = {"site icon", "site settings", "icon"})
    public void changePicture() throws Exception {

        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);

        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";
        userActions.createSite(TestData.email, TestData.pass, siteUrl);
        String icon = siteSettingsPage.siteIcon.findElement(driver).getAttribute("src");
        siteSettingsPage.uplodIcon(siteSettingsPage.iconPath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(siteSettingsPage.confirmPopUpButton)).click();
        String newIcon = siteSettingsPage.siteIcon.findElement(driver).getAttribute("src");
        Assert.assertFalse(icon.equals(newIcon), "Icon was not changed");
        userActions.deleteSite(siteUrl);
        new HeaderMenu(driver, wait).logout();
    }
}
