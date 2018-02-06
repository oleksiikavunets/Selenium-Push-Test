package com.selenium;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FromStringTerm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailService {

    private static GravitecServer gravitecServer;
    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = new FileInputStream("src/main/data/GRV.property");
            ConfigTest config = new ConfigTest();
            prop.load(input);
            int port = Integer.valueOf(config.getPort());
            int directPort = Integer.valueOf(config.getDirectPort());
            gravitecServer = new GravitecServer(port, directPort);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastMessageFromGravitec() throws Exception {
        ConfigTest config = new ConfigTest();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);

        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", (String) prop.get("email"),
                (String) prop.get("emailPass"));

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        System.out.println("Total Message:" + folder.getMessageCount());
        System.out.println("Unread Message:"
                + folder.getUnreadMessageCount());

        Message[] messages = null;
        boolean isMailFound = false;
        Message mailFromGod = null;
        //Search for mail from God
        for (int i = 0; i <= 120; i++) {
            messages = folder.search(new FromStringTerm(
                            config.pattern()),
                    folder.getMessages());
            int unseen = 0;
            for (Message message : messages) {
                if (!message.isSet(Flags.Flag.SEEN)) unseen++;
            }

            //Wait for 10 seconds
            if (unseen == 0) {
                System.out.println("spend " + i + " seconds to get email from gravitec");
                Thread.sleep(1000);
            } else {
                break;
            }
        }

        //Search for unread mail from God
        //This is to avoid using the mail for which
        //Registration is already done
        for (Message mail : messages) {
            if (!mail.isSet(Flags.Flag.SEEN)) {
                mailFromGod = mail;
                System.out.println("Message Count is: "
                        + mailFromGod.getMessageNumber());
                isMailFound = true;
            }
        }

        //Test fails if no unread mail was found from God
        if (!isMailFound) {
            throw new Exception(
                    "Could not find new mail from God :-(");

            //Read the content of mail and launch registration URL
        } else {
            String result = "";
            if (mailFromGod.isMimeType("text/plain") || mailFromGod.isMimeType("text/html")) {
                result = mailFromGod.getContent().toString();
            } else if (mailFromGod.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) mailFromGod.getContent();
                result = getTextFromMimeMultipart(mimeMultipart);
            } /*else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mailFromGod.writeTo(baos);
                result = baos.toString();
            }*/

            //Your logic to split the message and get the Registration URL goes here
            for (Message message : folder.getMessages()) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            folder.close(true);
            store.close();
            return result;
        }
    }


    private static String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public static String getRegistrationMail() {
        String registrationMail = null;

        for (int i = 0; i < 3; i++) {
            try {
                String mail = getLastMessageFromGravitec();

                if (mail.contains("Complete registration") ||
                        mail.contains("Zakończ rejestrację") ||
                        mail.contains("Активировать учетную запись") ||
                        mail.contains("Vollständige Anmeldung") ||
                        mail.contains("Активувати обліковий запис")) {
                    registrationMail = mail;
                    break;
                } else {
                    System.out.println("This is not registration mail:\n" + mail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registrationMail;
    }

    public static String getRecoverPasswordMail() {
        String recoverPasswordMail = null;

        for (int i = 0; i < 3; i++) {
            try {
                String mail = getLastMessageFromGravitec();

                if (mail.contains("Reset the password") ||
                        mail.contains("Resetuj hasło") ||
                        mail.contains("Сбросить пароль") ||
                        mail.contains("Passwort zurücksetzen") ||
                        mail.contains("Змінити пароль")) { //must put ukrainian text for kyivstar
                    recoverPasswordMail = mail;
                    break;
                } else {
                    System.out.println("This is not recover password mail:\n" + mail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return recoverPasswordMail;
    }

    public static String getCreatedSiteMail() {
        String createSiteMail = null;

        for (int i = 0; i < 3; i++) {
            try {
                String mail = getLastMessageFromGravitec();

                if (mail.toLowerCase().contains("the new website was registered!") ||
                        mail.toLowerCase().contains("nowa strona została zarejestrowana!") ||
                        mail.toLowerCase().contains("новый сайт создан в вашем аккаунте") ||
                        mail.toLowerCase().contains("die neue webseite wurde registriert!") ||
                        mail.toLowerCase().contains("новий сайт створений у вашому обліковому записі")) { //must put ukrainian text for kyivstar
                    createSiteMail = mail;
                    break;
                } else {
                    System.out.println("This is not created site mail:\n" + mail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createSiteMail;
    }

    public static String getConfirmationLink() throws Exception {
        String messageBody = getRegistrationMail();
        String confirmationLink;
//        if (ConfigTest.iTest.equals("7700") || ConfigTest.iTest.equals("GRV") || ConfigTest.iTest.equals("kyivstar7700") || ConfigTest.iTest.equals("kyivstar") || ConfigTest.iTest.equals("7600") || ConfigTest.iTest.equals("push2b")) {
            System.out.println(messageBody);
            confirmationLink = messageBody.split(" https://")[1].split("\\n")[0];
            //return  "https://" + messageBody;
//        } else {
//            Document doc = Jsoup.parse(messageBody);
//            Elements links = doc.select("a[href]");
//            confirmationLink = links.get(4).attr("href").split("https://")[1];
//        }
        return "https://" + confirmationLink;

    }


    public static String getRecoverLink() throws Exception {

        String messageBody = getRecoverPasswordMail();
        String recoverLink;
//        if (ConfigTest.iTest.equals("7700") || ConfigTest.iTest.equals("prod") || ConfigTest.iTest.equals("kyivstar7700") || ConfigTest.iTest.equals("kyivstar") || ConfigTest.iTest.equals("7600") || ConfigTest.iTest.equals("push2b")) {
            System.out.println(messageBody);
            recoverLink = messageBody.split("https://")[3].split("\\n")[0];
//        } else {
//            Document doc = Jsoup.parse(messageBody);
//            Elements links = doc.select("a[href]");
//            recoverLink = links.get(4).attr("href").split("https://")[1];
//        }
        return "https://" + recoverLink;
    }

    public static String getCreatedSiteUrl() throws Exception {
        String messagebody = getCreatedSiteMail();
        System.out.println(messagebody);
        String createdSite = messagebody.split(" https://")[1].split("\\n")[0];
        return "https://" + createdSite;
    }

    public static String getCreatedSiteUrl(String messagebody) throws Exception {
        System.out.println(messagebody);
        String createdSite = messagebody.split(" https://")[1].split("\\n")[0];
        return "https://" + createdSite;
    }


}
