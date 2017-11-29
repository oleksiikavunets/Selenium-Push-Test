package com.selenium;

import com.selenium.enums.Server;

import java.io.*;
import java.util.Properties;

/**
 * Created by Oleksii on 01.08.2017.
 */
public class ConfigTest {
    protected static Properties prop = new Properties();
    String propertyPath;
    int port;
    int directPort;
    String hostUrl;
    String apiUrl;
    String testSiteUrl;
    String adminLogin;
    String adminPass;
    String emailNumber;
    String password;
    String startUrl;
    String pattern;

    public static Server iTest;

    public static void setTestServer(Server serverToTest){
        iTest = serverToTest;
    }

    public ConfigTest(){
    }

    public ConfigTest(Server serverToTest){
        iTest = serverToTest;
    }

    private String setPath() {
        switch (iTest) {
            case GRV:
                propertyPath = "src/main/data/GRV.property";
                break;
            case WPUSH:
                propertyPath = "src/main/data/WPUSH.property";
                break;
            case GRV_7700:
                propertyPath = "src/main/data/GRV_7700.property";
                break;
            case GRV_7600:
                propertyPath = "src/main/data/GRV_7600.property";
                break;
            case P2B:
                propertyPath = "src/main/data/P2B.property";
                break;
            case WPUSH_7700:
                propertyPath = "src/main/data/WPUSH_7700.property";
        }
        return propertyPath;
    }

    public String getStartUrl(){
        switch (iTest){
            case WPUSH:
                startUrl = getHostUrl();
                break;
            default:
                startUrl = getHostUrl() + getPort();
        }
        return startUrl;
    }

    public String pattern(){
        switch (iTest){
            case WPUSH:
                pattern = ".*Wpush.*";
                break;
            case WPUSH_7700:
                pattern = ".*Wpush.*";
                break;
            case P2B:
                pattern = ".*Push2b*.";
                break;
            default:
                pattern = ".*Gravitec.net.*";
                break;
        }
    return pattern;
    }

    public int getPort(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            port = Integer.valueOf(prop.getProperty("hostPort"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
    public int getDirectPort(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            directPort = Integer.valueOf(prop.getProperty("directPort"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return directPort;
    }
    public String getHostUrl(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            hostUrl = prop.getProperty("hostUrl");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostUrl;
    }
    public String getApiUrl(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            apiUrl = prop.getProperty("apiUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiUrl;
    }

    public String getTestSiteUrl(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            testSiteUrl  = prop.getProperty("testSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testSiteUrl;
    }

    public String getAdminLogin(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            adminLogin  = prop.getProperty("adminLogin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adminLogin;
    }

    public String getAdminPass(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            adminPass  = prop.getProperty("adminPass");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adminPass;
    }

    public String getEmailNumber(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            emailNumber  = prop.getProperty("emailNumber");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emailNumber;
    }

    public void setEmailNumber(int num) throws IOException {

        try {
            OutputStream output = new FileOutputStream(setPath());
            prop.setProperty("emailNumber", String.valueOf(num));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getPassword(){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            password  = prop.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public void setPassword(String password) {
        try {
            OutputStream output = new FileOutputStream(setPath());
            prop.setProperty("password", password);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(password);
    }

    public void setHttpSite(String site, int testSiteNumber){
        try {
            OutputStream output = new FileOutputStream(setPath());
            prop.setProperty("httpSiteUrl", site);
            prop.setProperty("httpSiteNumber", String.valueOf(testSiteNumber));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHttpSiteNumber(){
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            siteNumber  = prop.getProperty("httpSiteNumber");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteNumber;
    }

    public void setHttpsSite(String site, int testSiteNumber){
        try {
            OutputStream output = new FileOutputStream(setPath());
            prop.setProperty("httpsSiteUrl", site);
            prop.setProperty("httpsSiteNumber", String.valueOf(testSiteNumber));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHttpsSiteNumber(){
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(setPath());
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
