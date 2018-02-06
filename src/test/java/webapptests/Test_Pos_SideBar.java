package webapptests;

import common.BaseTestClass;
import org.testng.annotations.Test;
import pageobjects.HeaderMenu;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SideBar;

import static testdata.TestData.email;
import static testdata.TestData.pass;

public class Test_Pos_SideBar extends BaseTestClass {

    @Test
    public void sideBarTest(){
        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);

        for(int i = 0; i < 10; i++) {
            SideBar sideBar = mainAdminPage.openSite();
            sideBar.openSubscribersPage();
            sideBar.openWelcomeMessagePage();
            sideBar.openSiteSettingsPage();

            new HeaderMenu(driver).clickLogo();
            System.out.println("FINISHED LOOP #" + i);
        }



        //sideBar.openSiteSettingsPage();
        //sideBar.openWelcomeMessagePage();
        //sideBar.openTagListPage();
        //sideBar.openCampaignHistoryPage();
        //sideBar.openReportsPage();

        //sideBar.openCreateCampaignPage();


    }
}
