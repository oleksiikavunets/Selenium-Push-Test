import actions.UserActions;
import actions.Verifier;
import com.selenium.ConfigTest;
import com.selenium.utils.RandomGenerator;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateWMPage;
import pageobjects.SideBar;
import pageobjects.WelcomeMessagePage;
import testdata.TestData;
import testrestrictions.BetaFeatures;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_EditWM extends SeleniumBaseClass {

    @Test(groups = {"WM"})
    public void editWMPos() throws Exception {

        UserActions userActions = new UserActions(driver, wait);
        SideBar sideBar = new SideBar(driver, wait);
        Verifier verifier = new Verifier();
        String testSite = TestData.newSitePattern + RandomGenerator.nextString() + ".com";


        if (BetaFeatures.verifyBetaToTest("WMwithButtonsAndBigImage")) {
            userActions.createSite(TestData.email, TestData.pass, testSite);
            WelcomeMessagePage welcomeMessagePage = sideBar.openWelcomeMessagePage();
            welcomeMessagePage.switchWM();
            CreateWMPage createWMPage = welcomeMessagePage.clickCreateNewWM();
            createWMPage.setTitle(TestData.welcomeMessageTitle);
            createWMPage.setText(TestData.welcomeMessageText);
            String oldIcon = createWMPage.getIconPreview().getAttribute("src");

            CreateWMPage.AdditionalActiveItems additionalActiveItems = createWMPage.openAdditionalActiveItems();
            additionalActiveItems.setButtons(TestData.button1Name, TestData.testSite, TestData.button2Name, TestData.testSite);
            additionalActiveItems.uploadBigImage(TestData.bigImage);

            verifier.assertEquals(createWMPage.getTitlePreview().getText(), TestData.welcomeMessageTitle, "Incorrect title on preview");
            verifier.assertEquals(createWMPage.getTextPreview().getText(), TestData.welcomeMessageText, "Incorrect text on preview");
            verifier.assertEquals(createWMPage.getButton1Preview().getText(), TestData.button1Name, "Incorrect button1 name on preview");
            verifier.assertEquals(createWMPage.getButton2Preview().getText(), TestData.button2Name, "Incorrect button2 name on preview");
            verifier.assertTrue(createWMPage.getBigImagePreview().isDisplayed(), "Big image not found in notification preview");

            createWMPage.saveWM();

            verifier.assertTrue(welcomeMessagePage.getWMTitle().isDisplayed(), "Welcome message is not displayed on page");
            verifier.assertNull(welcomeMessagePage.getCreateNewWMButton(), "Add new WM button is present on page when it should not");

            welcomeMessagePage.clickEditWM();

            String newTitle = "New " + TestData.welcomeMessageTitle;
            String newText = "New " + TestData.welcomeMessageText;

            createWMPage.clearTitle();
            createWMPage.clearText();
            createWMPage.setTitle(newTitle);
            createWMPage.setText(newText);
            String newIcon = createWMPage.uploadIconToWM(TestData.icon);

            //switching off additional active items
            additionalActiveItems.switchButton1();
            additionalActiveItems.switchButton2();
            additionalActiveItems.switchBIGImage();

            verifier.assertEquals(createWMPage.getTitlePreview().getText(), newTitle, "Incorrect new title on preview");
            verifier.assertEquals(createWMPage.getTextPreview().getText(), newText, "Incorrect new text on preview");
            verifier.assertNotEquals(newIcon, oldIcon, "Icon was not changed");
            verifier.assertNull(createWMPage.getButton1Preview(), "Button1 was not switched off");
            verifier.assertNull(createWMPage.getButton2Preview(), "Button2 was not switched off");
            verifier.assertNull(createWMPage.getBigImagePreview(), "Big image was not switched off");

            createWMPage.saveWM();
            welcomeMessagePage.clickEditWM();
            verifier.assertEquals(createWMPage.getTitlePreview().getText(), newTitle, "Incorrect new title on preview");
            verifier.assertEquals(createWMPage.getTextPreview().getText(), newText, "Incorrect new text on preview");
            verifier.assertNotEquals(newIcon, oldIcon, "Icon was not changed");
            verifier.assertNull(createWMPage.getButton1Preview(), "Button1 was not switched off");
            verifier.assertNull(createWMPage.getButton2Preview(), "Button2 was not switched off");
            verifier.assertNull(createWMPage.getBigImagePreview(), "Big image was not switched off");

            createWMPage.saveWM();
            welcomeMessagePage.deleteWM();



            userActions.deleteSite(testSite);
            verifier.assertTestPassed();
        } else {
            throw new SkipException(this.getClass().getSimpleName() + "Current funtionality is not deployed on " + ConfigTest.iTest);
        }

    }
}
