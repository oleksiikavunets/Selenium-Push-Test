package testconfigs.testdatamanagers;

import testconfigs.baseconfiguration.TestServerConfiguretion;

import java.io.*;
import java.util.Properties;

import static testconfigs.testdata.TestData.testEmail;
import static testconfigs.testdatamanagers.TestSiteManager.updateSiteOwnerPass;

public class TestUserManager {

    private static final String path = "src/main/resources/data/testusers/";
    private static Properties prop = new Properties();

    private static String getFullPath() {
        StringBuilder fullPath = new StringBuilder(path);

        switch (TestServerConfiguretion.iTest) {
            case GRV:
                fullPath.append("GRV_user.property");
                break;
            case GRV_7700:
                fullPath.append("GRV_7700_user.property");
                break;
            case GRV_7600:
                fullPath.append("GRV_7600_user.property");
                break;
            case WPUSH:
                fullPath.append("WPUSH_user.property");
                break;
            case WPUSH_7700:
                fullPath.append("WPUSH_7700_user.property");
                break;
            case P2B:
                fullPath.append("P2B_user.property");
                break;
            case UBR:
                fullPath.append("UBR_user.property");
        }
        return fullPath.toString();
    }

    public static String getEmail(){

        String email = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            email = prop.getProperty("email");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static int getEmailNumber(){

            String emailNumber = "";
            try {
                InputStream input = new FileInputStream(getFullPath());
                prop.load(input);
                emailNumber = prop.getProperty("emailNumber");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(emailNumber);
        }

    public static String getPassword(){

        String pass = "";
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            pass = prop.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pass;
    }

    public static void setEmail(String email, int emailNumber){
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("email", email);
            prop.setProperty("emailNumber", String.valueOf(emailNumber));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setEmailNumber(int emailNumber){
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("emailNumber", String.valueOf(emailNumber));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPassword(String pass) {
        try {
            InputStream input = new FileInputStream(getFullPath());
            prop.load(input);
            OutputStream output = new FileOutputStream(getFullPath());
            prop.setProperty("password", String.valueOf(pass));
            prop.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateSiteOwnerPass(testEmail, pass);
    }
}
