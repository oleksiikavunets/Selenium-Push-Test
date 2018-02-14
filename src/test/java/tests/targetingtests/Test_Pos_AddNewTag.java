package tests.targetingtests;

import actions.UserActions;
import com.selenium.utils.RandomGenerator;
import common.BaseTestClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.TagListPage;
import pageutils.Navigator;
import testconfigs.testdata.TestData;
import testconfigs.testdata.TestDataProvider;
import testutils.Listeners.LogListener;

import static testconfigs.testdatamanagers.TestDataManager.setSite;
import static testconfigs.testdatamanagers.TestDataManager.setTags;

@Listeners(LogListener.class)
public class Test_Pos_AddNewTag extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites", groups = {"subscription", "advanced settings", "tags"})
    public void addNewTagTest(String testSite) throws Exception {
        Navigator navigator = new Navigator(driver);
        String newTag = TestData.newTag;
        String[] newTags = {
                "newT1" + RandomGenerator.nextString(),
                "newT2" + RandomGenerator.nextString(),
                "newT3" + RandomGenerator.nextString()
        };
        driver.manage().deleteAllCookies();
        new UserActions(driver).addNewTag(testSite, newTags);
        setTags(newTags);
        setSite(testSite);
        new LogInPage(driver).login(TestData.email, TestData.pass);
        TagListPage tagListPage = navigator.open(TagListPage.class, testSite);
        tagListPage.searchForTag(newTags);
//        ArrayList<String> t = Arrays.copyOf(newTags);
//    Assert.assertTrue(tagListPage.searchForTag(newTags), "Could not find added tag/tags");
    }
}