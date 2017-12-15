import actions.UserActions;
import com.selenium.TestDataManager;
import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import testdata.TestData;
import testutils.Listeners.LogListener;

@Listeners(LogListener.class)
public class Test_Pos_AddNewTag extends BaseTestClass {

    @Parameters("browser")
    @Test (groups = {"subscription", "advanced settings", "tags"})
    public void addNewTag(@Optional("chrome") String browser) throws Exception {

        LogInPage logInPage = new LogInPage(driver);
        String testSite = TestData.testSite;
        String newTag = TestData.newTag;
        String[] newTags = {
                "newT1" + RandomGenerator.nextString(),
                "newT2" + RandomGenerator.nextString(),
                "newT3" + RandomGenerator.nextString()
        };
        driver.manage().deleteAllCookies();
        new UserActions(driver).addNewTag(browser, testSite, newTags);
        new TestDataManager().setTags(newTags).setSite(testSite);
        TagListPage tagListPage = logInPage.login(TestData.email, TestData.pass)
                .openSite(testSite).openTagListPage();
        Assert.assertTrue(tagListPage.searchForTag(newTags), "Could not find added tag/tags");
    }
}