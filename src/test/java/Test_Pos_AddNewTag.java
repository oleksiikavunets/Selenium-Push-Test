import actions.UserActions;
import com.selenium.ConfigTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.TagListPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_AddNewTag extends SeleniumBaseClass {

    @Parameters("browser")
    @Test (groups = {"subscription", "advanced settings", "tags"})
    public void addNewTag(@Optional("chrome") String browser) throws Exception {
        ConfigTest config = new ConfigTest();
        LogInPage logInPage = new LogInPage(driver, wait);
        TagListPage tagListPage = new TagListPage(driver, wait);
        UserActions userActions = new UserActions(driver, wait);
        MainAdminPage mainAdminPage = new MainAdminPage(driver, wait);
        String testSite = config.getTestSiteUrl();
        String newTag = TestData.newTag;

        driver.manage().deleteAllCookies();

        userActions.addNewTag(browser, testSite, newTag);
        logInPage.login(TestData.email, TestData.pass);
        mainAdminPage.openSite(testSite);
        tagListPage.openTagListPage();

        Boolean found = tagListPage.searchForTag(newTag);
        Assert.assertTrue(found, "Could not find added tag");
    }
}