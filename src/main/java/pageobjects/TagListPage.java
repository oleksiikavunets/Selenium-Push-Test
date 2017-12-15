package pageobjects;

import actions.Custom;
import actions.Timer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pageobjects.common.AbstractPage;
import webdriverconfiger.WaitManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pageutils.TextGetter.textOf;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class TagListPage extends AbstractPage{

    Wait<WebDriver> wait;

    public By tagListSideButton = By.cssSelector("span[ng-bind=\"'LMENU_TAGLIST' | translate\"]");
    public By tagsTUB = By.cssSelector("a[ng-bind*=\"'TAGLST_TAGS'\"]");
    public By tagListTUB = By.cssSelector("a[ng-bind*=\"'TAGLST_TAGLIST' \"]");

    private By tagSearchMask = By.cssSelector("input[ng-model$='searchingName']");
    private By searchBtn = By.cssSelector("a[class^='btn'][ng-click*='searchByName']");
    public By tagName = By.cssSelector("span[ng-bind=\"tag.name\"]");
    public By tagList = By.xpath("//tag-list-common-lists//td[1]/span");
    public By pageButton = By.cssSelector("a[data-ng-bind=\"page\"]");
    public By endPaginationButton = By.cssSelector("a[ng-click*=\"vmPagination.getPages\"]");
    public By saveTagListButton = By.id("sa-title");
    public By useTLinNewCampButton = By.cssSelector("a[class*='btn-send']");
    public By okPopUpButton = By.cssSelector("button[class~='confirm'][ng-bind*=\"ok\"]");
    public By arrowButton = By.cssSelector("i[class*='fa-arrow-circle-right']");
    public By arrowButton2 = By.xpath("//*[@id=\"page-wrapper\"]//div[2]//tr[2]//i");
    public By tagInNewTagList = By.cssSelector("td[ng-bind='tag.name']");
    public By tagInNewTagList2 = By.xpath("//*[@id=\"page-wrapper\"]//tag-list//div[2]/table//tr[4]/td[1]");
    public By tagListNameInput = By.cssSelector("input[ng-model*=\"tagListName\"]");
    public By newTLpopUp = By.cssSelector("h2[ng-bind-html=\"title | translate\"]>em");
    public By errorTagListPopUp = By.cssSelector("h2[ng-bind-html='title | translate']");

    public TagListPage(final WebDriver driver) {
        super(driver);
        this.wait = new WaitManager(driver).getWait();
    }
    public TagListPage(final WebDriver driver, int timeout) {
        super(driver);
        this.wait = new WaitManager(driver).getWait(timeout);
    }

    public TagListPage setTagListName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListNameInput)).sendKeys(name);
        return this;
    }

    public TagListPage saveNewTagList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveTagListButton)).click();
        return this;
    }

    public TagListPage closePopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(okPopUpButton));
        return this;
    }

    public TagListPage addTagsToNewTL() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(arrowButton));
        List<WebElement> arrows = driver.findElements(arrowButton);

        for (WebElement arrow : arrows) {
            arrow.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList));
        return this;
    }

    public TagListPage addTagsToNewTL(String... tags) {
        for (String t : tags) {
            searchForTag(t);
            wait.until(ExpectedConditions.visibilityOfElementLocated(arrowButton)).click();
        }
        return this;
    }

    public TagListPage addTagsToNewTL(int count) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(arrowButton));
        List<WebElement> arrows = driver.findElements(arrowButton);
        int i = 0;
        for (WebElement arrow : arrows) {
            arrow.click();
            i++;
            if (i == count) break;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList));
        if (count > 1) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList2));
        }
        return this;
    }

    public CreateCampaignPage useTLinNewCampaign() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(useTLinNewCampButton)).click();
//        new SideBar(driver, wait).manageErrorPop();
        return new CreateCampaignPage(driver);
    }

    public boolean searchForTag(String... tags) {
        boolean found;
        ArrayList<Boolean> founds = new ArrayList<>(Arrays.asList());

        for (int i = 0; i < tags.length; i++) {

            tagSearchMask.findElement(driver).sendKeys(tags[i]);
            searchBtn.findElement(driver).click();
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(tagName));
            Timer.waitSeconds(1);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tagName));
            List<String> foundTagsNames = textOf(driver.findElements(tagName));
            if (foundTagsNames.contains(tags[i])) {
                founds.add(true);
            } else {
                founds.add(false);
            }
            tagSearchMask.findElement(driver).clear();
        }
        if (!founds.contains(false)) {
            found = true;
        } else {
            found = false;
        }
        return found;
    }

    public boolean searchForTagList(String tagListName) {
        boolean found = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListTUB)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagList));
        List<WebElement> pages = driver.findElements(pageButton);

        for (WebElement page : pages) {
            List<WebElement> tagLists = driver.findElements(tagList);
            for (WebElement tagList : tagLists) {
                if (tagList.getText().equals(tagListName)) {
                    found = true;
                    Assert.assertEquals(tagListName, tagList.getText());
                    break;
                }
            }
            tagLists.clear();
            page.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(tagList));
        }
        return found;
    }

    public int getPagesAmount() {
        int amount;
        try {
            WebElement element = driver.findElement(endPaginationButton);
            new Custom(driver).clickAt(element);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(endPaginationButton));
            Timer.waitSeconds(0.5);
        } catch (org.openqa.selenium.NoSuchElementException e) {
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageButton));
        List<WebElement> pages = driver.findElements(pageButton);
        amount = Integer.valueOf(pages.get(pages.size() - 1).getText());

        return amount;
    }
}
