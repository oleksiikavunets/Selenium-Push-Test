package tests.statistictests;

import common.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LogInPage;
import pageobjects.SubscribersPage;
import pageutils.Navigator;
import testconfigs.testdata.TestDataProvider;

import static testconfigs.testdata.TestData.email;
import static testconfigs.testdata.TestData.pass;

public class Test_Pos_SubscribersCalculatorTest extends BaseTestClass {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "getPermanentTestSites")
    public void subscribersCalculatorTest(String testSite) {

        Navigator navigator = new Navigator(driver);
        new LogInPage(driver).login(email, pass);
        SubscribersPage subscribersPage = navigator.open(SubscribersPage.class, testSite);
        subscribersPage.switchLifeTimeStats();

        subscribersPage.getSubsAmountByBrowsers().forEach((k, v) -> Assert.assertEquals(
                subscribersPage.selectBrowser(k)
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN CALCULATION RESULT BY BROWSERS IS NOT EQUAL TO STATISTICS ON PAGE"));
        subscribersPage.resetSelects();

        subscribersPage.getSubsAmountByCountry().forEach((k, v) -> Assert.assertEquals(
                subscribersPage.selectCountry(k)
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN IN CALCULATION RESULT BY COUNTRIES IS NOT EQUAL TO STATISTICS ON PAGE"));
        subscribersPage.resetSelects();

        subscribersPage.getSubsAmountByCity().forEach((k, v) -> Assert.assertEquals(
                subscribersPage.selectCity(k)
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN IN CALCULATION RESULT BY CITIES IS NOT EQUAL TO STATISTICS ON PAGE"));
        subscribersPage.resetSelects();

        subscribersPage.getSubsAmountByOs().forEach((k, v) -> Assert.assertEquals(
                subscribersPage.selectOs(k)
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN CALCULATION RESULT BY OS IS NOT EQUAL TO STATISTICS ON PAGE"));
        subscribersPage.resetSelects();

        subscribersPage.getSubsAmountByAliases().forEach((k, v) -> Assert.assertEquals(
                subscribersPage.selectAliases(k)
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN CALCULATION RESULT BY ALIASES IS NOT EQUAL TO STATISTICS ON PAGE"));

/*
        List<HashMap<String, String>> subs = new ArrayList<>(Arrays.asList(
                subscribersPage.getSubsAmountByBrowsers(),
                subscribersPage.getSubsAmountByCountry(),
                subscribersPage.getSubsAmountByCity(),
                subscribersPage.getSubsAmountByOs(),
                subscribersPage.getSubsAmountByAliases()
        ));

        subs.forEach(s ->
                s.forEach((k, v) ->
                Assert.assertEquals(
                subscribersPage.selectByCondition(k)
                        .get(subs.indexOf(s))
                        .countSubscribers()
                        .getSubsCountBySelectedConditions(), v,
                "AMOUNT OF SUBSCRIBERS IN CALCULATION RESULT IS NOT EQUAL TO STATISTICS ON PAGE")));
        subscribersPage.resetSelects();
        */
    }
}

