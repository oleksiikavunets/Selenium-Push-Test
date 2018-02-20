package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.LogInPage;
import pageobjects.WelcomeMessagePage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotEquals;
import static testconfigs.testdata.TestData.*;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class Test_Pos_CropIconInNewWelcomeMessage extends BaseTestClass {

    @Test
    public void cropIconInNewWelcomeMessageTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        WelcomeMessagePage welcomeMessagePage = navigationUtil.open(WelcomeMessagePage.class, getOldTestSiteUrl(HTTPS));
        CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM();

        String oldIcon = createWMPage.getIconPreview().getAttribute("src");

        crop.uploadIcon(icon);

        String newIcon = createWMPage.getIconPreview().getAttribute("src");

        assertNotEquals(newIcon, oldIcon, "NEW ICON WAS NOT CROPPED AND LOADED...................................");
    }

}
