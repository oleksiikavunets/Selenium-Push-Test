package com.selenium.utils;

import testconfigs.baseconfiguration.TestServerConfiguretion;
import pageobjects.SiteManagerPage;

public class NameGenerator {

    public static String generateNewHttpsSiteName(int siteNumber) {
        String server = String.valueOf(TestServerConfiguretion.iTest).toLowerCase().replace("_", "");
        return "https://" + server + siteNumber + SiteManagerPage.siteDomain;
    }

    public static String generateNewHttpSiteName(int siteNumber) {
        String server = String.valueOf(TestServerConfiguretion.iTest).toLowerCase().replace("_", "");
        return "http://" + server + siteNumber + SiteManagerPage.siteDomain;
    }

    public static String generateNewUserEmail(int emailNumber){
        return "grovitek+" + emailNumber + "@gmail.com";
    }
}
