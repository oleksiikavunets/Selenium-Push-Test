import actions.UserActions;
import com.selenium.ConfigTest;
import com.selenium.utils.RandomGenerator;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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
public class Test_Pos_CreateTagList extends BaseTestClass {

    @Parameters("browser")
    @Test(groups = {"tag list"})
    public void createTagList(@Optional("chrome") String browser) throws Exception {

        ConfigTest configTest = new ConfigTest();
        String tagListName = TestData.tagListName;
        String testSite = configTest.getTestSiteUrl();
        String[] tags = new String[]{
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(browser, TestData.testSite, tags);/** This block adds a new tag. If you do not need any new tag make it a comment*/
        TagListPage tagListPage = new LogInPage(driver).login(TestData.email, TestData.pass)
                .openSite(testSite).openTagListPage()
                .addTagsToNewTL()
                .setTagListName(tagListName)
                .saveNewTagList();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(tagListPage.newTLpopUp)).getText(), tagListName);
        tagListPage.closePopUp();
        driver.navigate().refresh();
        tagListPage.searchForTagList(tagListName);
        System.out.println("Tag List '" + tagListName + "' exists))");
    }
}
