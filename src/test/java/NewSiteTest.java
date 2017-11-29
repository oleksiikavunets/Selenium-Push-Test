import actions.SiteManager;
import actions.Timer;
import org.testng.annotations.Test;

public class NewSiteTest extends SeleniumBaseClass{

    @Test
    public void newSiteTest(){
        String newSite = "http://selenium31.mpsdevelopment.com";

        String site = SiteManager.createNewSite(newSite);
        Timer.waitSeconds(5);

        driver.get(SiteManager.SITE_MANAGER_URL);
        SiteManager.setScript(site, "<script yuioutyrfgiyg/>");
        Timer.waitSeconds(5);
        System.out.println("DONE!!");
    }
}
