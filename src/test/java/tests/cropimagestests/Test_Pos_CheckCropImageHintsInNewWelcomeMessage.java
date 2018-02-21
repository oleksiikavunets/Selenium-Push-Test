package tests.cropimagestests;

import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.*;
import pageutils.CropUtil;
import pageutils.NavigationUtil;
import testconfigs.testdata.CropHints;

import java.util.List;
import java.util.Map;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertEquals;
import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;
import static testconfigs.testdatamanagers.TestSiteManager.getOldTestSiteUrl;

public class Test_Pos_CheckCropImageHintsInNewWelcomeMessage extends BaseTestClass {

    @Test
    public void checkCropImageHintsInNewCampaignTest() {

        String iconNotDist = "src/imgs/icons/80x80.jpg";
        String iconZoomed = "src/imgs/icons/100x100.jpg";
        String iconDist = "src/imgs/icons/100x120.jpg";
        String bigImgNotDist = "src/imgs/bigimages/360x240.jpg";
        String bigImgZoomed = "src/imgs/bigimages/363x242.jpg";
        String bigImgDist = "src/imgs/bigimages/360x241.jpg";
        NavigationUtil navigation = new NavigationUtil(driver);
        CropUtil cropUtil = new CropUtil(driver);

        HeaderMenu headerMenu = new HeaderMenu(driver);

        new LogInPage(driver).login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        headerMenu.switchFirstLanguage();
        navigation.open(WelcomeMessagePage.class, getOldTestSiteUrl(HTTPS))
                .clickCreateNewWM()
                .openAdditionalActiveItems()
                .switchBIGImage();

        cropUtil.uploadBigImg(bigImgNotDist)
                .uploadIcon(iconNotDist);

        for (int i = 1; i <= langs.size(); i++) {
            String lang = headerMenu.checkLanguage();
            String iconNotDistoredHintActual = cropUtil.getIconNotDistHint();
            String bigImageNotDistoredHintActual = cropUtil.getBigImgNotDistHint();
            Map<String, String> iconNotDistoredHintsExpected = CropHints.getImgNotDistHint();
            Map<String, String> bigImageNotDistoredHintsExpected = CropHints.getImgNotDistHint();
            assertEquals(iconNotDistoredHintActual, iconNotDistoredHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED ICON 80X80.");
            assertEquals(bigImageNotDistoredHintActual, bigImageNotDistoredHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED BIG IMAGE 360X240.");
            if (i == langs.size()) break;
            headerMenu.switchLanguage(i);
        }
        cropUtil.cancelIconUpload().cancelBigImageUpload();
        headerMenu.openLanguageDropDown().switchFirstLanguage();
        cropUtil.uploadIcon(iconDist).uploadBigImg(bigImgDist);

        for (int i = 1; i <= langs.size(); i++) {
            String lang = headerMenu.checkLanguage();
            String iconDistoredHintActual = cropUtil.getIconWillBeDistHint();
            String bigImageDistoredHintActual = cropUtil.getBigImgWillBeDistHint();
            Map<String, String> iconDistoredHintsExpected = CropHints.getImgWillBeDistHint();
            Map<String, String> bigImageDistoredHintsExpected = CropHints.getImgWillBeDistHint();
            assertEquals(iconDistoredHintActual, iconDistoredHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED ICON 100X120.");
            assertEquals(bigImageDistoredHintActual, bigImageDistoredHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED BIG IMAGE 360X241.");
            if (i == langs.size()) break;
            headerMenu.switchLanguage(i);
        }

        cropUtil.cancelIconUpload().cancelBigImageUpload();
        headerMenu.openLanguageDropDown().switchFirstLanguage();
        cropUtil.uploadIcon(iconZoomed).uploadBigImg(bigImgZoomed);

        for (int i = 1; i <= langs.size(); i++) {
            String lang = headerMenu.checkLanguage();
            String iconZoomedHintActual = cropUtil.getIconWillZoomHint();
            String bigImageZoomedHintActual = cropUtil.getBigImgWillZoomHint();
            Map<String, String> iconZoomedHintsExpected = CropHints.getImgWillZoomHint();
            Map<String, String> bigImageZoomedHintsExpected = CropHints.getImgWillZoomHint();
            assertEquals(iconZoomedHintActual, iconZoomedHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED ICON 100X100.");
            assertEquals(bigImageZoomedHintActual, bigImageZoomedHintsExpected.get(lang), "INCORRECT HINT DISPLAYED WHEN UPLOADED BIG IMAGE 363X242.");
            if (i == langs.size()) break;
            headerMenu.switchLanguage(i);
        }
    }
}
