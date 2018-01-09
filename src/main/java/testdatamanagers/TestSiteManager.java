package testdatamanagers;

import com.selenium.ConfigTest;

import java.io.*;
import java.util.Properties;

public class TestSiteManager extends ConfigTest {

    private static String path = "src/main/data/testsites/";

    private static Properties prop = new Properties();

    private static String getFullPath() {
        StringBuilder fullPath = new StringBuilder(path);

        switch (ConfigTest.iTest) {
            case GRV:
                fullPath.append("GRV_sites.property");
                break;
            case GRV_7700:
                fullPath.append("GRV_7700_sites.property");
                break;
            case GRV_7600:
                fullPath.append("GRV_7600_sites.property");
                break;
            case WPUSH:
                fullPath.append("WPUSH_sites.property");
                break;
            case WPUSH_7700:
                fullPath.append("WPUSH_7700_sites.property");
                break;
            case P2B:
                fullPath.append("P2B_sites.property");
                break;
        }
        return fullPath.toString();
    }

    public static String getTestSiteUrl(){
        String testSiteUrl = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            testSiteUrl  = prop.getProperty("testSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testSiteUrl;
    }

    public static String getHttpSiteUrl(){
        String httpSite = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            httpSite  = prop.getProperty("httpSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpSite;
    }

    public static String getHttpsSiteUrl(){
        String httpSite = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            httpSite  = prop.getProperty("httpsSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpSite;
    }

    public static void setHttpsSite(String site, int testSiteNumber){
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("httpsSiteUrl", site);
            prop.setProperty("httpsSiteNumber", String.valueOf(testSiteNumber));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setHttpSite(String site, int testSiteNumber){
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("httpSiteUrl", site);
            prop.setProperty("httpSiteNumber", String.valueOf(testSiteNumber));
            prop.list(System.err);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHttpSiteNumber(){
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            siteNumber  = prop.getProperty("httpSiteNumber");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteNumber;
    }

    public static String getHttpsSiteNumber(){
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            siteNumber  = prop.getProperty("httpsSiteNumber");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteNumber;
    }
}
