package com.selenium.utils;

import com.selenium.ConfigTest;
import pageobjects.SiteManagerPage;

public class NameGenerator {

    public static String generateNewHttpsSiteName(int siteNumber) {
        String server = String.valueOf(ConfigTest.iTest).toLowerCase().replace("_", "");
        return "https://" + server + siteNumber + SiteManagerPage.siteDomain;
    }

    public static String generateNewHttpSiteName(int siteNumber) {
        String server = String.valueOf(ConfigTest.iTest).toLowerCase().replace("_", "");
        return "http://" + server + siteNumber + SiteManagerPage.siteDomain;
    }

    public static String generateNewUserEmail(int emailNumber){
        return "grovitek+" + emailNumber + "@gmail.com";
    }
}
