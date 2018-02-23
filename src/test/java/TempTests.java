import com.selenium.GravitecServer;
import com.selenium.pojo.User;
import utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

/**
 * Created by Rayant on 11.04.2017.
 */
public class TempTests {
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static GravitecServer gravitecServer = new GravitecServer(7600, 7783);


    public static void main(String[] args) {

        String json = "{\n" +
                "  \"id\": \"1565007693133905920\",\n" +
                "  \"e\": \"grovitek+33@gmail.com\",\n" +
                "  \"r\": \"OPERATOR\",\n" +
                "  \"t\": {\n" +
                "    \"id\": \"1554501473609449472\",\n" +
                "    \"n\": \"Free\",\n" +
                "    \"p\": 0.0,\n" +
                "    \"m\": \"1000\",\n" +
                "    \"o\": \"0\",\n" +
                "    \"services\": [\n" +
                "      \"TAGS\",\n" +
                "      \"SDK\",\n" +
                "      \"CUSTOM_USER_SEGMENTS\",\n" +
                "      \"UNLIMITED_WEBSITES\",\n" +
                "      \"ADVANCED_SEGMENTS\",\n" +
                "      \"STANDARD_SEGMENTS\",\n" +
                "      \"UNLIMITED_CAMPAIGN\",\n" +
                "      \"GEOLOCATION\",\n" +
                "      \"BROWSERS\",\n" +
                "      \"REST_API\",\n" +
                "      \"UNLIMITED_NOTIFICATIONS\",\n" +
                "      \"ALIASES\"\n" +
                "    ],\n" +
                "    \"gravitecServices\": {},\n" +
                "    \"support\": [\n" +
                "      \"EMAIL\",\n" +
                "      \"INFORMATION_MATERIALS\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"newTariff\": {\n" +
                "    \"id\": \"1554501473609449472\",\n" +
                "    \"n\": \"Free\",\n" +
                "    \"p\": 0.0,\n" +
                "    \"m\": \"1000\",\n" +
                "    \"o\": \"0\",\n" +
                "    \"services\": [\n" +
                "      \"TAGS\",\n" +
                "      \"SDK\",\n" +
                "      \"CUSTOM_USER_SEGMENTS\",\n" +
                "      \"UNLIMITED_WEBSITES\",\n" +
                "      \"ADVANCED_SEGMENTS\",\n" +
                "      \"STANDARD_SEGMENTS\",\n" +
                "      \"UNLIMITED_CAMPAIGN\",\n" +
                "      \"GEOLOCATION\",\n" +
                "      \"BROWSERS\",\n" +
                "      \"REST_API\",\n" +
                "      \"UNLIMITED_NOTIFICATIONS\",\n" +
                "      \"ALIASES\"\n" +
                "    ],\n" +
                "    \"gravitecServices\": {},\n" +
                "    \"support\": [\n" +
                "      \"EMAIL\",\n" +
                "      \"INFORMATION_MATERIALS\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"ed\": \"2027-04-18\",\n" +
                "  \"rd\": \"2017-04-18T12:27:21.917\",\n" +
                "  \"discount\": 0,\n" +
                "  \"currentDebt\": 0,\n" +
                "  \"frozen\": false,\n" +
                "  \"disabled\": false,\n" +
                "  \"overlimit\": false,\n" +
                "  \"fa\": \"0\",\n" +
                "  \"lang\": \"EN\",\n" +
                "  \"pushesSend\": \"0\",\n" +
                "  \"pushesClicked\": \"0\",\n" +
                "  \"pushesDelivered\": \"0\",\n" +
                "  \"messagesSend\": \"0\",\n" +
                "  \"paymentStatus\": \"OK\",\n" +
                "  \"nextPaymentDate\": \"2017-05-01T12:27:21.917\",\n" +
                "  \"lastPaymentAmount\": \"0\"\n" +
                "}";


        User user = JsonUtils.fromJson(User.class, json);
        System.out.println(user.toString());

    }

}
