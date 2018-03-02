package tests.statistictests;

import actions.Verifier;
import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.MainAdminPage;
import pageobjects.SubscribersPage;
import pageutils.NavigationUtil;
import testconfigs.testdata.TestDataProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

public class Test_Pos_SubscriberStatistics extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites")
    public void allSubscribersAmountTest(String testSite) {

        NavigationUtil navigationUtil = new NavigationUtil(driver);

        MainAdminPage mainAdminPage = new LogInPage(driver).login(email, pass);
        int amountOfSiteSubscribers = mainAdminPage.getAmountOfSiteSubscribers(testSite);
        System.out.println(amountOfSiteSubscribers);
        SubscribersPage subscribersPage = navigationUtil.open(SubscribersPage.class, testSite);
        subscribersPage.switchLifeTimeStats();
        int amountOfAllSubscribers = subscribersPage.getAmountOfSubscribers();
        Assert.assertEquals(amountOfSiteSubscribers, amountOfAllSubscribers);
        int amountOfAllSubs = subscribersPage.switchLifeTimeStats().getAmountOfSubscribers();
        int amountOfUnsbSubs = subscribersPage.getAmountOfUnsbSubs();
        int amounOfNewSubs = subscribersPage.getAmountOfNewSubs();
        Assert.assertEquals(amountOfAllSubs + amountOfUnsbSubs, amounOfNewSubs);
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites")
    public void periodFilterTest(String testSite) {

        NavigationUtil navigationUtil = new NavigationUtil(driver);
        Verifier verifier = new Verifier();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        String weekAgo = LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        String monthAgo = LocalDateTime.now().minusMonths(1).plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));

        new LogInPage(driver).login(email, pass);
        SubscribersPage subscribersPage = navigationUtil.open(SubscribersPage.class, testSite)
                .switchLifeTimeStats();
        verifier.assertEquals(subscribersPage.getAllDatesInGraph().get(0), now, "Incorrect end date in Life Time Period..............................");

        subscribersPage.switchMonthStats();
        List<String> monthGraph = subscribersPage.getAllDatesInGraph();
        verifier.assertTrue(monthGraph.size() >= 28 && monthGraph.size() <= 31, "Graph does not display month period correctly...........");
        verifier.assertEquals(monthGraph.get(0), now, "Incorrect end date in Month Period........");
        verifier.assertEquals(monthGraph.get(monthGraph.size() - 1), monthAgo, "Incorrect start date in Month Period........");

        subscribersPage.switchTodayStats();
        List<String> todayGraph = subscribersPage.getAllDatesInGraph();
        verifier.assertEquals(todayGraph.size(), 1, "Today graph displays more than 1 day.................");
        verifier.assertEquals(todayGraph.get(0), now, "Incorrect date of Today Period...................");

        subscribersPage.switchYesterdayStats();
        List<String> yesterdayGraph = subscribersPage.getAllDatesInGraph();
        verifier.assertEquals(yesterdayGraph.size(), 1, "Yesterday Graph displays more than 1 day.............");
        verifier.assertEquals(yesterdayGraph.get(0), yesterday, "Incorrect date of Yesterday Period....................");

        subscribersPage.swithWeekStats();
        List<String> weekGraph = subscribersPage.getAllDatesInGraph();
        verifier.assertEquals(weekGraph.size(), 7, "Week in Graph has more than 7 days..................");
        verifier.assertEquals(weekGraph.get(weekGraph.size() - 1), weekAgo, "Incorrect start date of Week Period.................");
        verifier.assertEquals(weekGraph.get(0), now, "Incorrect end date of Week Period.................");

        verifier.assertTestPassed();
    }
}
