import actions.Verifier;
import testutils.Listeners.LogListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.ErrorMessages;
import testdata.TestData;
import testrestrictions.BetaFeatures;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Neg_SendMessage extends SeleniumBaseClass {


    @Test(groups = { "negative", "send push" })// checks all error messages on page "Create Campaign"
    public void sendMessageNegative() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        HeaderMenu headerMenu = new HeaderMenu(driver, wait);
        BetaFeatures betaFeatures = new BetaFeatures();
        ErrorMessages errorMessages = new ErrorMessages();
        Verifier verifier = new Verifier();

        HashMap<String, String> requiredField = errorMessages.getRequiredField();
        HashMap<String, String> invalidLinkFormat = errorMessages.getInvalidLinkFormat();
        String siteLang;


        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();

        SideBar sideBar = mainAdminPage.openSite();
        CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();

        createCampaignPage.setUrlToRedirect("1!@#$2345qwerцуке");
        createCampaignPage.uploadIcon(TestData.bigIcon);
        //Thread.sleep(10000);

        if (betaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
            CreateCampaignPage.AdditionalActiveItems activeItems = createCampaignPage.openAdditionalActiveItems();
            activeItems.switchButton1();
            activeItems.switchButton2();
            activeItems.setButton1URL("11212");
            activeItems.setButton2URL("23421");
            System.out.println("OK");
        }
        createCampaignPage.clickSendPush();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();

            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.requiredTitle)).getText(), requiredField.get(siteLang));
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.requiredText)).getText(), requiredField.get(siteLang));
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.linkFormat)).getText(), invalidLinkFormat.get(siteLang));


            if (betaFeatures.verifyBetaToTest("buttonsAndBigImage")) {
                verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.button1TitelError)).getText(), requiredField.get(siteLang));
                verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.button1URLError)).getText(), invalidLinkFormat.get(siteLang));
                verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.button2TitleError)).getText(), requiredField.get(siteLang));
                verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignPage.button2URLError)).getText(), invalidLinkFormat.get(siteLang));
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
