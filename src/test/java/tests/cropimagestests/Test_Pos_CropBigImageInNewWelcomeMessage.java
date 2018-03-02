package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.LogInPage;
import pageobjects.WelcomeMessagePage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;
import testconfigs.testdatamanagers.TestSiteManager;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotNull;
import static testconfigs.testdata.TestData.*;

public class Test_Pos_CropBigImageInNewWelcomeMessage extends BaseTestClass {

    @Test
    public void cropBigImageInNewWelcomeMessageTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        WelcomeMessagePage welcomeMessagePage = navigationUtil.open(WelcomeMessagePage.class,  new TestSiteManager().getOldTestSiteUrl(HTTPS));
        CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM();

        CreateWMPage.AdditionalActiveItems activeItems = createWMPage.openAdditionalActiveItems();
        activeItems.switchBIGImage();
        crop.uploadAndCropBigImg(bigImage);

        assertNotNull(createWMPage.getBigImagePreview().getAttribute("src"), "BIG IMAGE WAS NOT CROPPED AND LOADED...................................");
    }
}
