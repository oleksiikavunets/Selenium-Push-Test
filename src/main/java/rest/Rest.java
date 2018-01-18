package rest;

import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Rest {

    public String login(){

        String accessToken = null;
        Map<String, ?> requestPOSTParam = null;

        Response response =
                given().auth().oauth2(accessToken, OAuthSignature.HEADER)
                        .contentType(ContentType.JSON).accept(ContentType.JSON)
                        .body(requestPOSTParam).log().all()
                        .when().post("api_path")
                        .then().log().all().statusCode(201)
                        .extract().response();

        return response.getCookie("accessToken");

    }


}
