package pageutils;

import testconfigs.baseconfiguration.TestServerConfiguretion;
import org.openqa.selenium.WebDriver;
import pageobjects.HeaderMenu;
import pageobjects.MainAdminPage;
import pageobjects.common.AbstractAdminPage;
import pageobjects.common.AbstractOuterPage;
import pageobjects.common.AbstractPage;
import pageobjects.common.annotations.PartialPath;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class NavigationUtil {
    private WebDriver driver;

    public NavigationUtil(WebDriver driver){
        this.driver = driver;
    }

    public <T extends AbstractAdminPage> T open(Class<T> page , String site) {
        driver.get(new TestServerConfiguretion().getStartUrl() + getPartialPath(page).replace("SITE_ID", getSiteID(site)));
        new HeaderMenu(driver).waitForBeingLogged();
        return (T)getPageInstance(page);
    }

    public <T extends AbstractOuterPage> T open(Class<T> page) {
        driver.get(new TestServerConfiguretion().getStartUrl() + getPartialPath(page));
        return (T)getPageInstance(page);
    }

    private boolean onMainPage(){
        return !driver.getCurrentUrl().contains("sites/") ? true : false;
    }

    private String getSiteID(String site){
        return onMainPage() ? new MainAdminPage(driver).getSiteId(site) : driver.getCurrentUrl().split("/sites/")[1].split("/")[0];
    }

    private <T extends AbstractPage> String getPartialPath(Class<T> page){
        return Optional.ofNullable(page.getAnnotation(PartialPath.class))
                .map(PartialPath::value)
                .map(e -> String.format("%s", e))
                .orElse(null);
    }

    private <T extends  AbstractPage> Object getPageInstance(Class<T> page){
        Object obj = null;
        try {
            Constructor constructor  = page.getConstructor(WebDriver.class);
            constructor.setAccessible(true);
            obj = constructor.newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
