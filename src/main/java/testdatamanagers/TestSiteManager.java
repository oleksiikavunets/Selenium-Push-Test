package com.selenium;

import java.io.*;
import java.util.Properties;

public class TestSiteManager extends ConfigTest{

    private static Properties prop = new Properties();

    public  String getHttpSiteUrl(){
        String httpSite = "";
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            httpSite  = prop.getProperty("httpSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpSite;
    }
    public String getHttpsSiteUrl(){
        String httpSite = "";
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            httpSite  = prop.getProperty("httpsSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpSite;
    }

    public void setHttpsSite(String site, int testSiteNumber){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
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

    public void setHttpSite(String site, int testSiteNumber){
        try {
            InputStream input = new FileInputStream(setPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(setPath());
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
