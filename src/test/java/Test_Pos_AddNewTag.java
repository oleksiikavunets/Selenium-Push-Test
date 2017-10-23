import actions.UserActions;
import com.selenium.ConfigTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.TagListPage;
import testdata.TestData;

public class Test_Pos_AddNewTag extends SeleniumBaseClass {

    @Test (groups = {"subscription", "advanced settings", "tags"})
    public void addNewTag() throws Exception {
        ConfigTest config = new ConfigTest();
        LogInPage logInPage = new LogInPage(driver, wait);
        TagListPage tagListPage = new TagListPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);
        String testSite = config.getTestSiteUrl();
        String newTag = TestData.newTag;

        driver.manage().deleteAllCookies();

        userActions.addNewTag(testSite, newTag);
        logInPage.login(TestData.email, TestData.pass);
        mainAdminPage.openSite(testSite);
        tagListPage.openTagListPage();

        Boolean found = tagListPage.searchForTag(newTag);
        Assert.assertTrue(found, "Could not find added tag");
        new HeaderMenu(driver, wait).logout();
    }
}