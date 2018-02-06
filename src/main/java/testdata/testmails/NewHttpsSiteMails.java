package testdata.testmails;

import com.selenium.enums.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.selenium.enums.Server.*;
import static com.selenium.enums.Server.P2B;
import static com.selenium.enums.Server.WPUSH_7700;

public class NewHttpsSiteMails {

    private List<String> gravitecEN = new ArrayList<>(Arrays.asList(
            "The new website was registered!",
            "Dear Customer,",
            "You've just added a new website to Gravitec.net dashboard:",
            "To complete integration, please, follow the instructions below.",
            "Step 1. Add the following code to the main HTML template of your website pages before between the and tags. The higher the better:",
            "Step 2. Copy SDK files (manifest.json",
            "and push-worker.js",
            "to the root folder of your website.",
            "Go to site settings",
            "Best regards, Gravitec.net team",
            "Do you have any questions? Feel free to ask them by replying to this email.",
            "unsubscribe"
    ));
    private List<String> gravitecUA = new ArrayList<>(Arrays.asList(
            "Новый сайт создан в вашем аккаунте",
            "Здравствуйте! В вашем аккаунте в Gravitec.net был добавлен новый сайт:",
            "Чтобы интегрировать Ваш сайт с сервисом и начать собирать push-подписчиков, пожалуйста, выполните следующие действия.",
            "Шаг 1. Скопируйте приведенный ниже код (скрипт) в HTML шаблон Вашего сайта между тегами и . Чем выше тем лучше:",
            "Шаг 2. Добавьте файлы manifest.json",
            "и push-worker.js",
            "в корневой каталог Вашего сайта.",
            "Открыть настройки сайта",
            "С уважением, команда Gravitec.net",
            "Если у Вас есть вопросы или Вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылки"
    ));
    private List<String> gravitecRU = new ArrayList<>(Arrays.asList(
            "Новый сайт создан в вашем аккаунте",
            "Здравствуйте! В вашем аккаунте в Gravitec.net был добавлен новый сайт:",
            "Чтобы интегрировать Ваш сайт с сервисом и начать собирать push-подписчиков, пожалуйста, выполните следующие действия.",
            "Шаг 1. Скопируйте приведенный ниже код (скрипт) в HTML шаблон Вашего сайта между тегами и . Чем выше тем лучше:",
            "Шаг 2. Добавьте файлы manifest.json",
            "и push-worker.js",
            "в корневой каталог Вашего сайта.",
            "Открыть настройки сайта",
            "С уважением, команда Gravitec.net",
            "Если у Вас есть вопросы или Вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылки"
    ));
    private List<String> gravitecPL = new ArrayList<>(Arrays.asList(
            "Nowa strona została zarejestrowana!",
            "Drogi Kliencie,",
            "Właśnie dodałeś nową stronę w Panelu Administracyjnym Gravitec.net",
            "Poniżej przesyłamy instrukcje pozwalające na zakończenie integracji strony.",
            "Krok 1. Dodaj poniższy kod do głównego szablonu HTML pomiędzy znakami oraz . Im wyżej tym lepiej:",
            "Krok 2. Skopiuj pliki SDK (manifest.json",
            "oraz push-worker.js",
            "do głównego foldera (root) na serwerze Twojej strony.",
            "Przejdź do ustawień strony",
            "Pozdrawiamy serdecznie, Zespół Gravitec.net",
            "Jakieś pytania? Zadaj je odpowiadając na tego maila.",
            "desubskrybuj"
    ));
    private List<String> gravitecDE = new ArrayList<>(Arrays.asList(
            "Die neue Webseite wurde registriert!",
            "Sehr geehrter Kunde,",
            "du hast soeben eine neue Website zum Gravitec.net Dashboard hinzugefügt:",
            "Um die Integration zu beenden, folgen bitte den Anweisungen unten.",
            "Schritt 1. Fügen der HTML-Vorlage und den folgenden Code hinzu. Je höher desto besser:",
            "Schritt 2. Kopiere die SDK-Dateien (manifest.json",
            "und push-worker.js",
            "in den Stammordner deiner Website.",
            "Gehe zu den Seiteneinstellungen",
            "Mit freundlichen Grüßen, das Gravitec.net Team",
            "Irgendwelche Fragen? Wir helfen gerne! Schreibe uns einfach eine E-mail.",
            "abmelden"
    ));

    private List<String> push2bRU = new ArrayList<>(Arrays.asList(
            "Новый сайт создан в вашем аккаунте",
            "Здравствуйте! В вашем аккаунте в push2b.com был добавлен новый сайт:",
            "Чтобы интегрировать Ваш сайт с сервисом и начать собирать push-подписчиков, пожалуйста, выполните следующие действия.",
            "Шаг 1. Скопируйте приведенный ниже код (скрипт) в HTML шаблон Вашего сайта между тегами и . Чем выше тем лучше:",
            "Шаг 2. Добавьте файлы manifest.json",
            "и push-worker.js",
            "в корневой каталог Вашего сайта.",
            "Открыть настройки сайта",
            "С уважением, команда push2b.com",
            "Если у Вас есть вопросы или Вам нужна помощь в использовании сервиса, напишите нам в ответе на это письмо.",
            "отписаться от рассылки"
    ));

    private List<String> wpushUK = new ArrayList<>(Arrays.asList(
            "Новий сайт створений у Вашому обліковому записі",
            "Вітаю! До Вашого облікового запису в wpush.biz було додано новий сайт:",
            "Для того, щоб інтегрувати Ваш сайт з сервісом та почати збирати підписників, скопіюйте вказаний нижче код (скрипт) у HTML шаблон Вашого сайту між тегами и . Чим вище, тим краще:",
            "Відкрити налаштування сайту",
            "З повагою, команда wpush.biz",
            "Якщо у Вас виникли питання або Вам потрібна допомога у використанні сервісу, напишіть нам у відповідь на цей лист.",
            "Скасувати підписку"
    ));

    private HashMap<String, List> gravitecMails = new HashMap<>();
    private HashMap<String, List> wpushMails = new HashMap<>();
    private HashMap<String, List> push2bMails = new HashMap<>();
    private HashMap<String, List> mails;

    public HashMap<String, List> getMails(Server serverUnderTest) {
        if (serverUnderTest.equals(GRV)
                || serverUnderTest.equals(GRV_7700)
                || serverUnderTest.equals(GRV_7600)
                || serverUnderTest.equals(UBR)) {
            mails = getGravitecMails();
        } else if (serverUnderTest.equals(WPUSH) ||
                serverUnderTest.equals(WPUSH_7700)) {
            mails = getWpushMails();
        } else if (serverUnderTest.equals(P2B)) {
            mails = getPush2bMails();
        }
        return mails;
    }

    private HashMap<String, List> getGravitecMails() {
        gravitecMails.put("en", gravitecEN);
        gravitecMails.put("pl", gravitecPL);
        gravitecMails.put("ua", gravitecUA);
        gravitecMails.put("ru", gravitecRU);
        gravitecMails.put("de", gravitecDE);
        return gravitecMails;
    }

    private HashMap<String, List> getWpushMails() {
        wpushMails.put("ru", wpushUK);
        wpushMails.put("uk", wpushUK);
        return wpushMails;
    }

    private HashMap<String, List> getPush2bMails() {
        push2bMails.put("en", push2bRU);
        push2bMails.put("pl", push2bRU);
        push2bMails.put("ru", push2bRU);
        push2bMails.put("de", push2bRU);
        return push2bMails;
    }
}
