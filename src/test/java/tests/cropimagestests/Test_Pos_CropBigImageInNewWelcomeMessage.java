package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.LogInPage;
import pageobjects.WelcomeMessagePage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotNull;
import static testconfigs.testdata.TestData.*;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class Test_Pos_CropBigImageInNewWelcomeMessage extends BaseTestClass {

    @Test
    public void cropBigImageInNewWelcomeMessageTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        WelcomeMessagePage welcomeMessagePage = navigationUtil.open(WelcomeMessagePage.class, getOldTestSiteUrl(HTTPS));
        CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM();

        CreateWMPage.AdditionalActiveItems activeItems = createWMPage.openAdditionalActiveItems();
        activeItems.switchBIGImage();
        crop.uploadBigImg(bigImage);

        assertNotNull(createWMPage.getBigImagePreview().getAttribute("src"), "BIG IMAGE WAS NOT CROPPED AND LOADED...................................");
    }
}
