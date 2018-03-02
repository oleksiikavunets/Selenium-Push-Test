package tests.cropimagestests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.LogInPage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;
import testconfigs.testdatamanagers.TestSiteManager;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertNotNull;
import static testconfigs.testdata.TestData.*;

public class Test_Pos_CropBigImageInNewCampaign extends BaseTestClass{

    @Test
    public void cropBigImageInNewCampaignTest(){

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        CropUtil crop = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        CreateCampaignPage createCampaignPage = navigationUtil.open(CreateCampaignPage.class,  new TestSiteManager().getOldTestSiteUrl(HTTPS));

        CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.openAdditionalActiveItems();
        activeItems.switchBIGImage();
        crop.uploadAndCropBigImg(bigImage);

        CreateCampaignPage.NotificationPreview preview = createCampaignPage.new NotificationPreview();

        assertNotNull(preview.getBigImagePreview().getAttribute("src"), "BIG IMAGE WAS NOT CROPPED AND LOADED...................................");
    }
}
