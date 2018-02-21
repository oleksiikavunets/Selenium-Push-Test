package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotEquals;
import static testconfigs.testdata.TestData.*;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class Test_Pos_CropIconInNewCampaign extends BaseTestClass {

    @Test
    public void cropIconInNewWelcomeMessageTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        CreateCampaignPage createCampaignPage = navigationUtil.open(CreateCampaignPage.class, getOldTestSiteUrl(HTTPS));

        CreateCampaignPage.NotificationPreview preview = createCampaignPage.new NotificationPreview();

        String iconBefore = preview.getIconPreview().getAttribute("src");

        crop.uploadAndCropIcon(icon);

        String iconAfter = preview.getIconPreview().getAttribute("src");

        assertNotEquals(iconAfter, iconBefore,  "NEW ICON WAS NOT CROPPED AND LOADED...................................");
    }
}
