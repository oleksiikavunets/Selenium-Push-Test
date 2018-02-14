package testconfigs.testdatamanagers;

import testconfigs.baseconfiguration.TestServerConfiguretion;
import com.selenium.enums.Protocol;

import java.io.*;
import java.util.Properties;

import static com.selenium.enums.Protocol.HTTP;

public class TestSiteManager extends TestServerConfiguretion {

    private static String path = "src/main/resources/data/testsites/";

    private static Properties prop = new Properties();

    private static String getFullPath() {
        StringBuilder fullPath = new StringBuilder(path);

        switch (TestServerConfiguretion.iTest) {
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
            case UBR:
                fullPath.append("UBR_sites.property");
                break;
        }
        return fullPath.toString();
    }

    public static String getOldTestSiteUrl(Protocol protocol) {
        String testSiteUrl = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            testSiteUrl = protocol == HTTP ? prop.getProperty("testHttpSiteUrl") : prop.getProperty("testHttpsSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testSiteUrl;
    }

    public static String getNewTestSiteUrl(Protocol protocol) {
        String testSiteUrl = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            testSiteUrl = protocol == HTTP ? prop.getProperty("httpSiteUrl") : prop.getProperty("httpsSiteUrl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testSiteUrl;
    }

    public static void setHttpsSite(String site, int testSiteNumber, String ownerEmail, String ownerPass) {
        testSiteNumber += 2;
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("httpsSiteUrl", site);
            prop.setProperty("httpsSiteNumber", String.valueOf(testSiteNumber));
            prop.setProperty("httpsSiteOwner", ownerEmail + ":" + ownerPass);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setHttpSite(String site, int testSiteNumber, String ownerEmail, String ownerPass) {
        testSiteNumber += 2;
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("httpSiteUrl", site);
            prop.setProperty("httpSiteNumber", String.valueOf(testSiteNumber));
            prop.setProperty("httpSiteOwner", ownerEmail + ":" + ownerPass);
            prop.list(System.err);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getSiteOwner(String site){
        return site.contains("https://") ? getHttpsSiteOwner() : getHttpSiteOwner();
    }

    private static String[] getHttpSiteOwner() {
        String[] owner = new String[2];
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            String ownerCredentials = prop.getProperty("httpSiteOwner");
            System.out.println(ownerCredentials);
            owner[0] = ownerCredentials.split(":")[0];
            owner[1] = ownerCredentials.split(":")[1];
            prop.list(System.err);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return owner;
    }

    private static String[] getHttpsSiteOwner() {
        String[] owner = new String[2];
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            String ownerCredentials = prop.getProperty("httpsSiteOwner");
            owner[0] = ownerCredentials.split(":")[0];
            owner[1] = ownerCredentials.split(":")[1];
            prop.list(System.err);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return owner;
    }

    public static String getHttpSiteNumber() {
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            siteNumber = prop.getProperty("httpSiteNumber");
            prop.list(System.err);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteNumber;
    }

    public static String getHttpsSiteNumber() {
        String siteNumber = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            siteNumber = prop.getProperty("httpsSiteNumber");
            prop.list(System.err);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteNumber;
    }

    public static void updateSiteOwnerPass(String email, String pass) {
        try {
            prop.load(new FileInputStream(getFullPath()));
            if (prop.getProperty("httpsSiteOwner").split(":")[0].equals(email)) {
                prop.setProperty("httpsSiteOwner", email + ":" + pass);
            }
            if (prop.getProperty("httpSiteOwner").split(":")[0].equals(email)) {
                prop.setProperty("httpSiteOwner", email + ":" + pass);
            }
            prop.store(new FileOutputStream(getFullPath()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
