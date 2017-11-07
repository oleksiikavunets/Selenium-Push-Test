package com.selenium;

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

//    public static String iTest = "prod";
//    public static String iTest = "7600";
    public static String iTest = "7700";
//    public static String iTest = "kyivstar";
//    public static String iTest = "push2b";
//    public static String iTest = "kyivstar7700";

    public ConfigTest(){

    }


    public String setPath() {
        switch (iTest) {
            case ("prod"):
                propertyPath = "src/main/data/prod.property";
                break;

            case ("kyivstar"):
                propertyPath = "src/main/data/kyivstar.property";
                break;

            case ("7700") :
                propertyPath = "src/main/data/7700.property";
                break;

            case ("7600") :
                propertyPath = "src/main/data/7600.property";
                break;
            case ("push2b") :
                propertyPath = "src/main/data/push2b.property";
                break;
            case ("kyivstar7700") :
                propertyPath = "src/main/data/kyivstar7700.property";
        }
        return propertyPath;
    }

    public String getStartUrl(){
        switch (iTest){
            case ("kyivstar"):
                startUrl = getHostUrl();
                break;
            default:
                startUrl = getHostUrl() + getPort();
        }
        return startUrl;
    }

    public String pattern(){
        switch (iTest){
            case ("kyivstar") :
                pattern = ".*Wpush.*";
                break;
            case ("push2b") :
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
}
