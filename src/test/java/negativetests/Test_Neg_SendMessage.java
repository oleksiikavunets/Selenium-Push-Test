package negativetests;

import actions.Verifier;
import common.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import pageutils.Navigator;
import testdata.ErrorMessages;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

import java.util.HashMap;
import java.util.List;

import static testdata.TestData.testSite;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_SendMessage extends BaseTestClass {


    @Test(groups = {"negative", "send push"})// checks all error messages on page "Create Campaign"
    public void sendMessageNegativeTest() throws Exception {
        Navigator navigator = new Navigator(driver);
        HeaderMenu headerMenu = new HeaderMenu(driver);
        BetaFeatures betaFeatures = new BetaFeatures();
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();

        HashMap<String, String> requiredField = errorMessages.getRequiredField();
        HashMap<String, String> invalidLinkFormat = errorMessages.getInvalidLinkFormat();
        String siteLang;

        new LogInPage(driver).login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();

        CreateCampaignPage createCampaignPage = navigator.open(CreateCampaignPage.class, testSite)
                .setUrlToRedirect("1!@#$2345qwerцуке")
                .uploadIcon(TestData.bigIcon);
        CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.new AdditionalActiveItems();

        if (betaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
            createCampaignPage.openAdditionalActiveItems()
                    .switchButton1()
                    .switchButton2()
                    .setButton1URL("11212")
                    .setButton2URL("23421");
            System.out.println("OK");
        }
        createCampaignPage.clickSendPush();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();

            verifier.assertEquals(createCampaignPage.getTitleErrorMsg().getText(), requiredField.get(siteLang));
            verifier.assertEquals(createCampaignPage.getTextErrorMsg().getText(), requiredField.get(siteLang));
            verifier.assertEquals(createCampaignPage.getInvalidLinkFormatMsg().getText(), invalidLinkFormat.get(siteLang));


            if (betaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
                verifier.assertEquals(activeItems.getBtn1TitleError().getText(), requiredField.get(siteLang));
                verifier.assertEquals(activeItems.getBtn1UrlError().getText(), invalidLinkFormat.get(siteLang));
                verifier.assertEquals(activeItems.getBtn2TitleError().getText(), requiredField.get(siteLang));
                verifier.assertEquals(activeItems.getBtn2UrlError().getText(), invalidLinkFormat.get(siteLang));
                System.out.println("OK1");
            }
            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }
        verifier.assertTrue(createCampaignPage.getIconTooBigError().isDisplayed());
        //checks error "Img too big"
        verifier.assertTestPassed();

    }
}
