package tests.negativetests;

import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageobjects.CreateCampaignPage;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageutils.CropUtil;
import pageutils.NavigationUtil;
import testconfigs.testdata.ErrorMessages;
import testconfigs.testdatamanagers.TestSiteManager;

import java.util.List;
import java.util.stream.IntStream;

import static com.selenium.enums.Protocol.HTTPS;
import static org.testng.Assert.assertEquals;
import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

public class Test_Neg_CheckCropImageErrorsInNewCampaign extends BaseTestClass {

    @Test
    public void checkCropImageErrorsInNewCampaignTest() {

        String tooSmallIcon = "src/imgs/icons/79х79.jpg";
        String tooSmallBigImg = "src/imgs/bigimages/359x239.jpg";
        String tooBigImg201kb = "src/imgs/bigimages/201kb.jpg";

        NavigationUtil navigation = new NavigationUtil(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        CropUtil cropUtil = new CropUtil(driver);

        new LogInPage(driver).login(email, pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        headerMenu.switchFirstLanguage();
        navigation.open(CreateCampaignPage.class,  new TestSiteManager().getOldTestSiteUrl(HTTPS))
                .openAdditionalActiveItems()
                .switchBIGImage();
        cropUtil.uploadIcon(tooSmallIcon);
        IntStream.range(1, langs.size() + 1).limit(langs.size()).forEach(e -> {
            assertEquals(cropUtil.getIconTooSmallErr(), ErrorMessages.getIconTooSmallErrors().get(headerMenu.checkLanguage()), "INCORRECT ERROR MESSAGE DISPLAYED WHEN TOO SMALL ICON WAS UPLOADED.");
            if (e < langs.size()) headerMenu.switchLanguage(e);
        });
        headerMenu.switchFirstLanguage();
        cropUtil.uploadBigImg(tooSmallBigImg);
        IntStream.range(1, langs.size() + 1).limit(langs.size()).forEach(e -> {
            assertEquals(cropUtil.getBigImgTooSmallErr(), ErrorMessages.getBigImageTooSmallErrors().get(headerMenu.checkLanguage()), "INCORRECT ERROR MESSAGE DISPLAYED WHEN TOO SMALL BIG IMAGE WAS UPLOADED.");
            if (e < langs.size()) headerMenu.switchLanguage(e);
        });
        headerMenu.switchFirstLanguage();
        cropUtil.uploadIcon(tooBigImg201kb);
        IntStream.range(1, langs.size() + 1).limit(langs.size()).forEach(e -> {
            assertEquals(cropUtil.getIconTooBigErr(), ErrorMessages.getImageTooBigErrors().get(headerMenu.checkLanguage()), "INCORRECT ERROR MESSAGE DISPLAYED WHEN TOO BIG ICON WAS UPLOADED.");
            if (e < langs.size()) headerMenu.switchLanguage(e);
        });
        headerMenu.switchFirstLanguage();
        cropUtil.uploadBigImg(tooBigImg201kb);
        IntStream.range(1, langs.size() + 1).limit(langs.size()).forEach(e -> {
            assertEquals(cropUtil.getBigImgTooBigErr(), ErrorMessages.getImageTooBigErrors().get(headerMenu.checkLanguage()), "INCORRECT ERROR MESSAGE DISPLAYED WHEN TOO BIG IMAGE WAS UPLOADED.");
            if (e < langs.size()) headerMenu.switchLanguage(e);
        });
    }
}
