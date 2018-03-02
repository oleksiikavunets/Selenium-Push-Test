package tests.cropimagestests;

import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;
import testconfigs.testdata.CropRecommendations;
import testconfigs.testdatamanagers.TestSiteManager;

import java.util.List;
import java.util.Map;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertEquals;
import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

public class Test_Pos_CheckCropImageRecommendationsInNewCampaign extends BaseTestClass{

    @Test
    public void checkCropImageRecommendationsInNewCampaignTest(){

        NavigationUtil navigation = new NavigationUtil(driver);
        CropUtil cropUtil = new CropUtil(driver);
        new LogInPage(driver).login(email, pass);

        HeaderMenu headerMenu = new HeaderMenu(driver);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        headerMenu.switchFirstLanguage();

        navigation.open(CreateCampaignPage.class,  new TestSiteManager().getOldTestSiteUrl(HTTPS))
                .openAdditionalActiveItems()
                .switchBIGImage();

        for(int i = 1; i <= langs.size(); i++) {
            String lang = headerMenu.checkLanguage();
            String iconCropRecommendationActual = cropUtil.getIconCropRecommendation();
            String imageCropRecommendationActual = cropUtil.getBigImageCropRecommendation();

            Map<String, String> iconCropRecommendationsExpected = CropRecommendations.getIconCropRecommendations();
            Map<String, String> imageCropRecommendationsExpected = CropRecommendations.getBigImgCropRecommendations();

            assertEquals(iconCropRecommendationActual, iconCropRecommendationsExpected.get(lang), "INCORRECT ICON CROP RECOMMENDATION DISPLAYED.");
            assertEquals(imageCropRecommendationActual, imageCropRecommendationsExpected.get(lang), "INCORRECT BIG IMAGE CROP RECOMMENDATION DISPLAYED.");

            if(i == langs.size()) break;
            headerMenu.switchLanguage(i);
        }
    }
}
