package tests.usersitetests;

import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.SiteSettingsPage;
import testutils.Listeners.LogListener;

import static testconfigs.testdata.TestData.*;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_ChangeSiteIcon extends BaseTestClass {


    @Test (groups = {"site icon", "site settings", "icon"})
    public void changePictureTest() throws Exception {

        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
        UserActions userActions = new UserActions(driver);

        String siteUrl = newHttpSitePattern + RandomGenerator.nextString() + ".com";
        userActions.createSite(email, pass, siteUrl);
        String icon = siteSettingsPage.getSiteIcon().getAttribute("src");
        siteSettingsPage.uplodIcon(siteSettingsPage.iconPath);
        siteSettingsPage.clickConfirmPopUpButton();
        String newIcon = siteSettingsPage.getSiteIcon().getAttribute("src");
        Assert.assertFalse(icon.equals(newIcon), "Icon was not changed");
        userActions.deleteSite(siteUrl);
    }
}
