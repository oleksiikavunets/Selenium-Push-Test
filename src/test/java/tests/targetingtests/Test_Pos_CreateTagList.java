package tests.targetingtests;

import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

/**
 * Created by Oleksii on 31.07.2017.
 */

@Listeners(LogListener.class)
public class Test_Pos_CreateTagList extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"tag list"})
    public void createTagListTest(String testSite) throws Exception {
        Navigator navigator = new Navigator(driver);

        String tagListName = TestData.tagListName;
        String[] tags = new String[]{
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString(),
                "tag" + RandomGenerator.nextString()};

        new UserActions(driver).addNewTag(testSite, tags);/** This block adds a new tag. If you do not need any new tag make it a comment*/
        new LogInPage(driver).login(TestData.email, TestData.pass);
        TagListPage tagListPage = navigator.open(TagListPage.class, testSite)
                .addTagsToNewTL()
                .setTagListName(tagListName)
                .saveNewTagList();
        Assert.assertEquals(tagListPage.getNewTlPopUp().getText(), tagListName, "Wrong tag list name on pop up after saving");
        tagListPage.closePopUp();
        driver.navigate().refresh();
        tagListPage.searchForTagList(tagListName);
        System.out.println("Tag List '" + tagListName + "' exists))");
    }
}
