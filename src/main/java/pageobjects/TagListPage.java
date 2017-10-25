package pageobjects;

import actions.Custom;
import actions.Timer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

/**
 * Created by Oleksii on 18.07.2017.
 */
public class TagListPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    public By tagListSideButton = By.cssSelector("span[ng-bind=\"'LMENU_TAGLIST' | translate\"]");
    public By tagsTUB = By.cssSelector("a[ng-bind*=\"'TAGLST_TAGS'\"]");
    public By tagListTUB = By.cssSelector("a[ng-bind*=\"'TAGLST_TAGLIST' \"]");
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

    public TagListPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openTagListPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListSideButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagName));
    }

    public void setTagListName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListNameInput)).sendKeys(name);

    }

    public void saveNewTagList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveTagListButton)).click();
    }

    public void closePopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUpButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(okPopUpButton));

    }

    public void addTagsToNewTL() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(arrowButton));
        List<WebElement> arrows = driver.findElements(arrowButton);

        for (WebElement arrow : arrows) {
            arrow.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList));
    }

    public void addTagsToNewTL(int count) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(arrowButton));
        List<WebElement> arrows = driver.findElements(arrowButton);
        int i = 0;
        for (WebElement arrow : arrows) {
            arrow.click();
            i++;
            if (i == count) break;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList));
        if(count > 1){
            wait.until(ExpectedConditions.visibilityOfElementLocated(tagInNewTagList2));
        }

    }

    public CreateCampaignPage useTLinNewCampaign(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(useTLinNewCampButton)).click();
        return new CreateCampaignPage(driver, wait);
    }

    public boolean searchForTag(String newTag) {
        boolean found = false;
        List<WebElement> tags = driver.findElements(tagName);
        for (WebElement t : tags) {
            if (t.getText().equals(newTag)) {
                found = true;
            }
        }
        if (!found) {
            int maxPages = getPagesAmount();

            quit:
            for (int i = maxPages; i >= 0; i--) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(tagName));
                List<WebElement> tg = driver.findElements(tagName);
                for (WebElement t : tg) {
                    if (t.getText().equals(newTag)) {
                        found = true;
                        break quit;
                    }
                }
                wait.until(ExpectedConditions.visibilityOfElementLocated(pageButton));
                List<WebElement> pages = driver.findElements(pageButton);
                for (WebElement p : pages) {
                    if (Integer.valueOf(p.getText()) == i) {
                        p.click();
                        Timer.waitSeconds(0.5);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(tagName));
                        break;
                    }
                }
            }

        }
        return found;
    }

    public void searchForTagList(String tagListName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(tagListTUB)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagList));
        List<WebElement> pages = driver.findElements(pageButton);

        for (WebElement page : pages) {
            List<WebElement> tagLists = driver.findElements(tagList);
            for (WebElement tagList : tagLists) {
                if (tagList.getText().equals(tagListName)) {
                    Assert.assertEquals(tagListName, tagList.getText());
                    break;
                }
            }
            tagLists.clear();
            page.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(tagList));
        }
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
