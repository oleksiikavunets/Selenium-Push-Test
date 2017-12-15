import actions.Verifier;
import testutils.Listeners.LogListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.ErrorMessages;
import testdata.TestData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oleksii on 31.07.2017.
 */
@Listeners(LogListener.class)
public class Test_Neg_CreateTagList extends BaseTestClass {


    @Test(groups = {"negative", "tag list"})
    public void createTagListNegative() throws Exception {
        HeaderMenu headerMenu = new HeaderMenu(driver);

        Verifier verifier = new Verifier();
        ErrorMessages errorMessages = new ErrorMessages();

        HashMap<String, String> noTags = errorMessages.getNoTags();
        HashMap<String, String> noName = errorMessages.getNoName();
        String testSite = TestData.testSite;
        String siteLang;


        MainAdminPage mainAdminPage = new LogInPage(driver).login(TestData.email, TestData.pass);
        List<WebElement> langs = headerMenu.getAvailableLanguages();
        langs.get(0).click();
        TagListPage tagListPage = mainAdminPage.openSite(testSite)
                .openTagListPage();

        for (int i = 1; i <= langs.size(); i++) {
            tagListPage.saveNewTagList();

            siteLang = headerMenu.checkLanguage();
            String errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.errorTagListPopUp)).getText();
            verifier.assertEquals(errorPopUp, noTags.get(siteLang));
//            checks message on pop-up window "Tag-list must contain at least 2 tags"
            tagListPage.closePopUp();

            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }
        if (langs.size() > 1) {
            headerMenu.switchLanguage();
        }
        tagListPage.addTagsToNewTL(1);

        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.tagInNewTagList));
        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();

            tagListPage.saveNewTagList();
            String errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.errorTagListPopUp)).getText();
            verifier.assertEquals(errorPopUp, noTags.get(siteLang));

            tagListPage.closePopUp();
            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }
        if (langs.size() > 1) {
            headerMenu.switchLanguage();
        }
        tagListPage.addTagsToNewTL(2);
//        driver.findElement(tagListPage.arrowButton2).click();

        for (int i = 1; i <= langs.size(); i++) {
            siteLang = headerMenu.checkLanguage();
            tagListPage.saveNewTagList();
            verifier.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.errorTagListPopUp)).getText(), noName.get(siteLang));

            // checks message on pop-up window "Tag-list name is empty"
            tagListPage.closePopUp();

            if (i == langs.size()) break;

            headerMenu.switchLanguage(i);
        }

        verifier.assertTestPassed();


    }
}
