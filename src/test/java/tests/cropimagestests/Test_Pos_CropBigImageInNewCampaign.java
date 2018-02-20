package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotNull;
import static testconfigs.testdata.TestData.bigImage;
import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class Test_Pos_CropBigImageInNewCampaign extends BaseTestClass{

    @Test
    public void cropBigImageInNewCampaignTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        CreateCampaignPage createCampaignPage = navigationUtil.open(CreateCampaignPage.class, getOldTestSiteUrl(HTTPS));

        CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.openAdditionalActiveItems();
        activeItems.switchBIGImage();
        crop.uploadBigImg(bigImage);

        CreateCampaignPage.NotificationPreview preview = createCampaignPage.new NotificationPreview();

        assertNotNull(preview.getBigImagePreview().getAttribute("src"), "BIG IMAGE WAS NOT CROPPED AND LOADED...................................");
    }
}
