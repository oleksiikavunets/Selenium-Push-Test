import com.selenium.utils.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testdata.TestData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_CreateDelayedMessages extends SeleniumBaseClass {

    @Test
    public void createDelayedMessages() throws Exception {
//        UserActions userActions = new UserActions(driver, wait);
//        SideBar sideBar = new SideBar(driver, wait);


        LogInPage logInPage = new LogInPage(driver, wait);
        String testSite = TestData.testSite;
        String siteUrl = "http://" + RandomGenerator.nextString() + ".com";

        List<int[]> dates = getNextMonthDates();


//        userActions.createSite(siteUrl);

        for (int i = 0; i < dates.size(); i++) {
            int[] date = dates.get(i);
            int year = date[0];
            int month = date[1];
            int day = date[2];
            int hour = date[3];
            int minute = date[4];
            MainAdminPage mainAdminPage = logInPage.login(TestData.email, TestData.pass);
            SideBar sideBar = mainAdminPage.openSite(testSite);

            CreateCampaignPage createCampaignPage = sideBar.openCreateCampaignPage();
            String title = "Delayed date: " + String.valueOf(year + "." + month + "." + day);
            String text = "Delayed time: " + String.valueOf(hour + ":" + minute);
            createCampaignPage.setTitle(title);
            System.out.println(title);
            createCampaignPage.setText(text);
            createCampaignPage.setUrlToRedirect(siteUrl);
            createCampaignPage.setDateAndTime(year, month, day, hour, minute);
            CampaignHistoryPage campaignHistoryPage = createCampaignPage.sendPush();

            CampaignReportPage campaignReportPage = campaignHistoryPage.openMessage(title);
            Assert.assertTrue(campaignReportPage.verifyMessageDelayed());
            new HeaderMenu(driver, wait).logout();
        }


    }


    public List<int[]> getNextMonthDates() {
        List<int[]> dates = new ArrayList<>(Arrays.asList());
        /**if you want to set from next month
         LocalDate localDate = LocalDate.now().plusMonths(1);
         int maxDays = localDate.lengthOfMonth();
         int startDay = 1;
         */

        /**if you want to set from tomorrow till the end of month*/

        LocalDate localDate = LocalDate.now().plusDays(1);
        int maxDays = localDate.lengthOfMonth();
        int startDay = Integer.parseInt(localDate.format(DateTimeFormatter.ofPattern("d")));


        int month = Integer.parseInt(localDate.format(DateTimeFormatter.ofPattern("MM")));
        int year = Integer.parseInt(localDate.format(DateTimeFormatter.ofPattern("yyyy")));


        for (int day = startDay; day <= maxDays; day++) {
            for (int hour = 0; hour <= 23; hour++) {
                int minute = 0;
                while (minute <= 30) {
                    int[] date = {year, month, day, hour, minute};
                    dates.add(date);
                    minute += 30;
                }
            }
        }
        return dates;
//        for(int[] dt: dates){
//            for(int d: dt){
//                System.out.print(d + " ");
//            }
//            System.out.println();
//        }
    }
}
