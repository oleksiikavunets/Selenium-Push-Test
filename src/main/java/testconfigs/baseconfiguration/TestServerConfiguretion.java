package testconfigs.baseconfiguration;

import com.selenium.enums.Server;

import java.io.*;
import java.util.Properties;

import static com.selenium.enums.Server.*;

/**
 * Created by Oleksii on 01.08.2017.
 */
public class TestServerConfiguretion {
    private static Properties prop = new Properties();

    public static Server iTest = TestCofiguration.testServer;

    public static void setTestServer(Server serverToTest){
        iTest = serverToTest;
    }

    public TestServerConfiguretion(){
    }

    public TestServerConfiguretion(Server serverToTest){
        iTest = serverToTest;
    }

    private static String setPath() {
        return String.format("src/data/%s.property", iTest);
    }

    public String getStartUrl(){
        return (iTest == WPUSH || iTest == UBR) ? getHostUrl() : getHostUrl() + getPort();
    }

    public String pattern(){
        return (iTest == WPUSH || iTest == WPUSH_7700) ? ".*Wpush.*" :
                iTest == P2B ? ".*Push2b*." : ".*Gravitec.net.*";
    }

    public static int getPort(){
        int port = 0;
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
    public static int getDirectPort(){
        int directPort = 0;
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
    public static String getHostUrl(){
        String hostUrl = "";
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
    public static String getApiUrl(){
        String apiUrl = "";
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

    public static String getAdminLogin(){
        String adminLogin = "";
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

    public static String getAdminPass(){
        String adminPass = "";
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

}
