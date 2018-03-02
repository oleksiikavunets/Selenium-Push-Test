package testconfigs.testdatamanagers;

import testconfigs.baseconfiguration.TestServerConfiguretion;

import java.io.*;
import java.util.Properties;



public class TestUserManager {

    private  final String path = "src/data/testusers/";
    private  Properties prop = new Properties();

    private  String getFullPath() {
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

    public  String getEmail(){

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

    public  int getEmailNumber(){

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

    public  String getPassword(){

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

    public  void setEmail(String email, int emailNumber){
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

    public  void setEmailNumber(int emailNumber){
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

    public  void setPassword(String pass) {
        try {
            prop.load(new FileInputStream(getFullPath()));
            prop.setProperty("password", String.valueOf(pass));
            prop.store(new FileOutputStream(getFullPath()), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new TestSiteManager().updateSiteOwnerPass(new TestUserManager().getEmail(), pass);
    }
}
