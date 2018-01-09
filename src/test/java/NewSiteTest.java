
import actions.Timer;
import org.testng.annotations.Test;
import pageobjects.SiteManagerPage;

public class NewSiteTest extends BaseTestClass {

    @Test
    public void newSiteTest(){
        SiteManagerPage siteManagerPage = new SiteManagerPage(driver);
        String newSite = "http://selenium31.mpsdevelopment.com";

        String site = siteManagerPage.createNewSite(newSite);
        Timer.waitSeconds(5);

        driver.get(siteManagerPage.SITE_MANAGER_URL);
        siteManagerPage.setSiteDatas(site, "<script yuioutyrfgiyg/>");
        Timer.waitSeconds(5);
        System.out.println("DONE!!");
    }
}
