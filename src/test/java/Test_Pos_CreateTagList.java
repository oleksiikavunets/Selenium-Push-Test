import com.selenium.ConfigTest;
import testutils.Listeners.LogListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateTagList extends SeleniumBaseClass {


    @Test(groups = {"tag list"})
    public void createTagList() throws Exception {
        LogInPage logInPage = new LogInPage(driver, wait);
        ConfigTest configTest = new ConfigTest();

        String tagListName = TestData.tagListName;
        String testSite = configTest.getTestSiteUrl();

        //addNewTag(tagListPage.testSite, tagListPage.newTag);/** This block adds a new tag. If you do not need any new tag make it a comment*/
        MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
        SideBar sideBar = mainAdminPage.openSite(testSite);

        TagListPage tagListPage = sideBar.openTagListPage();
        tagListPage.addTagsToNewTL();
        tagListPage.setTagListName(tagListName);
        tagListPage.saveNewTagList();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.newTLpopUp)).getText(), tagListName);
        tagListPage.closePopUp();
        driver.navigate().refresh();
        tagListPage.searchForTagList(tagListName);
        System.out.println("Tag List '" + tagListName + "' exists))");
    }
}
