package tests.usersitetests;

import actions.UserActions;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.SiteSettingsPage;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_ChangeSiteIcon extends BaseTestClass {


    @Test (dataProviderClass = TestDataProvider.class, dataProvider = "getRandomSiteNames",groups = {"site icon", "site settings", "icon"})
    public void changePictureTest(String siteUrl) throws Exception {

        SiteSettingsPage siteSettingsPage = new SiteSettingsPage(driver);
        UserActions userActions = new UserActions(driver);

        userActions.createSite(email, pass, siteUrl);
        String icon = siteSettingsPage.getSiteIcon().getAttribute("src");
        siteSettingsPage.uplodIcon(siteSettingsPage.iconPath);
        siteSettingsPage.clickConfirmPopUpButton();
        String newIcon = siteSettingsPage.getSiteIcon().getAttribute("src");
        Assert.assertFalse(icon.equals(newIcon), "Icon was not changed");
        userActions.deleteSite(siteUrl);
    }
}
