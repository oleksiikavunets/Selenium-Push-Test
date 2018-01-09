package com.selenium;

import java.io.*;
import java.util.Properties;

public class TestDataManager {
    private final String path = "src/main/data/testdata/";
    private static Properties prop = new Properties();

    private String getFullPath() {
        StringBuilder fullPath = new StringBuilder(path);

        switch (ConfigTest.iTest) {
            case GRV:
                fullPath.append("GRV_data.property");
                break;
            case GRV_7700:
                fullPath.append("GRV_data_7700.property");
                break;
            case GRV_7600:
                fullPath.append("GRV_data_7600.property");
                break;
            case WPUSH:
                fullPath.append("WPUSH_data.property");
                break;
            case WPUSH_7700:
                fullPath.append("WPUSH_7700_data.property");
                break;
            case P2B:
                fullPath.append("P2B_data.property");
                break;
        }
        return fullPath.toString();
    }

    public String getSite() {
        String site = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            site = prop.getProperty("site");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return site;
    }

    public String getAlias() {
        String alias = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            alias = prop.getProperty("alias");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alias;
    }

    public String getTag() {
        String tag = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            tag = prop.getProperty("tag1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tag;
    }

    public String[] getTags() {
        String[] tags = new String[3];
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            tags[0] = prop.getProperty("tag");
            tags[1] = prop.getProperty("tag1");
            tags[2] = prop.getProperty("tag2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tags;
    }

    public TestDataManager setAlias(String alias) {
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("alias", alias);
            prop.list(System.err);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TestDataManager setTags(String...tags) {
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            for(int i = 1; i <= tags.length; i++){
                prop.setProperty("tag" + i, tags[i-1]);
            }
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TestDataManager setSite(String site) {
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("site", site);
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
