package com.selenium;

import com.selenium.pojo.Alias;
import com.selenium.pojo.Message;
import com.selenium.pojo.Tag;
import com.selenium.pojo.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import testconfigs.baseconfiguration.TestServerConfiguretion;
import testconfigs.testdatamanagers.TestSiteManager;
import utils.JsonUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static com.selenium.enums.Protocol.HTTP;

/**
 * Created by Rayant on 07.04.2017.
 */
public class GravitecServer {
    TestServerConfiguretion config = new TestServerConfiguretion();

    private String token;
    private int port = config.getPort();
    private int directPort = config.getDirectPort();
    private long testSiteId;
    private JSONParser parser = new JSONParser();
    private String apiUrl = config.getApiUrl();
    private String hostUrl = config.getHostUrl();
    private String testSiteUrl =  new TestSiteManager().getOldTestSiteUrl(HTTP);

    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setMaxConnPerRoute(1000)
            .setMaxConnTotal(100)
            .build();

    public GravitecServer(int port, int directPort) {
        this.port = port;
        this.directPort = directPort;
    }

    public void login(String login, String pass) {
        String path = "/api/users/login";
        String loginJson = "{\"e\":\"" + login + "\",\"p\":\"" + pass + "\"}";
        System.out.println(loginJson);
        try {
            HttpPost httpPost = new HttpPost(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + port + path);
            httpPost.setEntity(new StringEntity(loginJson));
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getFirstHeader("Set-Cookie") != null && response.getFirstHeader("Set-Cookie").getValue() != null)
                token = response.getFirstHeader("Set-Cookie").getValue().replace("accessToken=", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkPass(String login, String pass) {
        String path = "/api/users/login";
        String loginJson = "{e:\"" + login + "\",p:\"" + pass + "\"}";
        System.out.println(loginJson);
        try {
            HttpPost httpPost = new HttpPost(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + port + path);
            httpPost.setEntity(new StringEntity(loginJson));
            HttpResponse response = httpClient.execute(httpPost);
            return response.getStatusLine().getStatusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTestSiteUrl(String url) {
        String path = "/api/sites/";
        String jsonResponse = getResponse(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject respObj = null;
        try {
            respObj = (JSONObject) jsonParser.parse(jsonResponse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray sitesJson = (JSONArray) respObj.get("data");
        for (Object o : sitesJson) {
            JSONObject obj = (JSONObject) o;
            if ((obj.get("u")).equals(url)) {
                testSiteId = Long.valueOf((String) obj.get("id"));
            }
        }
    }

    public String getSitePicUrl(String url) {
        String path = "/api/sites/";
        String jsonResponse = getResponse(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject respObj = null;
        try {
            respObj = (JSONObject) jsonParser.parse(jsonResponse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray sitesJson = (JSONArray) respObj.get("data");
        for (Object o : sitesJson) {
            JSONObject obj = (JSONObject) o;
            if ((obj.get("u")).equals(url)) {
                return (String) obj.get("icon");
            }
        }
        return null;
    }

    public Long getSiteId(String url) {
        String path = "/api/sites/";
        String jsonResponse = getResponse(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject respObj = null;
        try {
            respObj = (JSONObject) jsonParser.parse(jsonResponse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray sitesJson = (JSONArray) respObj.get("data");
        for (Object o : sitesJson) {
            JSONObject obj = (JSONObject) o;
            if ((obj.get("u")).equals(url)) {
                return Long.valueOf((String) obj.get("id"));
            }
        }
        return null;
    }

    public boolean checkIfSiteUrlExists(String url) {
        System.out.println(url);
        System.out.println("I check " + url);
        JSONParser jsonParser = new JSONParser();
        JSONObject respObj = null;
        String path = "/api/sites/";
        String jsonResponse = getResponse(path);
        System.out.println("jsonResponse" + jsonResponse);
        try {
            respObj = (JSONObject) jsonParser.parse(jsonResponse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray sitesJson = (JSONArray) respObj.get("data");
        for (Object o : sitesJson) {
            JSONObject obj = (JSONObject) o;
            System.out.println(o);
            if ((obj.get("u")).equals(url)) {
                return true;
            }
        }
        return false;
    }

    private String getResponse(String path) {
        try {
            System.out.println(apiUrl + port + path);
            HttpGet httpGet = new HttpGet(apiUrl + port + path);
            httpGet.addHeader("Accept", "application/json");
            httpGet.addHeader("Connection", "Keep-Alive");
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(httpGet);

            String myResponse = EntityUtils.toString(response.getEntity());
            System.out.println(myResponse);
            return myResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long sendMessage() {
        Long id = null;
        String path = "/api/sites/" + testSiteId + "/send";
        String body = Message.MESSAGE_EXAMPLE;
        try {
            HttpPost httpPost = new HttpPost(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + path);
            httpPost.addHeader("Cookie", "accessToken=" + token);
            httpPost.setEntity(new StringEntity(body));
            HttpResponse response = httpClient.execute(httpPost);
            JSONObject respObj = (JSONObject) parser.parse(EntityUtils.toString(response.getEntity()));
            id = Long.valueOf((String) respObj.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Message> getMessages(Long siteId) {
        List<Message> messages = new ArrayList<>();
        String path = "/api/sites/" + siteId + "/messages?page=1&per_page=1000";
        try {
            HttpGet httpPost = new HttpGet(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + port + path);
            httpPost.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(httpPost);
            JSONObject respObj = (JSONObject) parser.parse(EntityUtils.toString(response.getEntity()));
            JSONArray jsonMessages = (JSONArray) respObj.get("data");
            messages = JsonUtils.getListFromJson(Message[].class, jsonMessages.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Message getMessage(Long messageId) {
        Message message = new Message();
        String path = "/api/messages/" + messageId;
        try {
            HttpGet httpPost = new HttpGet(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + port + path);
            httpPost.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(httpPost);
            String resp = EntityUtils.toString(response.getEntity());
            message = JsonUtils.fromJson(Message.class, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<Tag>();
        String path = "/api/sites/" + testSiteId + "/tags?perPage=1000";
        try {
            HttpGet http = new HttpGet(apiUrl + port + path);
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            JSONObject respJson = (JSONObject) parser.parse(EntityUtils.toString(response.getEntity()));
            JSONArray tagsJson = (JSONArray) respJson.get("data");
            for (Object obj : tagsJson) {
                JSONObject tagJson = (JSONObject) obj;
                Tag tag = new Tag(Long.valueOf((String) tagJson.get("id")), (String) tagJson.get("name"));
                tags.add(tag);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    public User getUserFromStatus() {
        User user = null;
        String path = "/api/users/status";
        try {
            HttpGet http = new HttpGet(apiUrl + port + path);
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            String resp = EntityUtils.toString(response.getEntity());
            user = JsonUtils.fromJson(User.class, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Alias> getAliases(String name) {
        List<Alias> aliases = new ArrayList<Alias>();
        String path = "/api/sites/" + testSiteId + "/aliases?text=" + name;
        try {
            HttpGet http = new HttpGet(apiUrl + port + path);
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            JSONArray tagsJson = (JSONArray) parser.parse(EntityUtils.toString(response.getEntity()));
            for (Object obj : tagsJson) {
                JSONObject tagJson = (JSONObject) obj;
                Alias tag = new Alias(Long.valueOf((String) tagJson.get("id")), (String) tagJson.get("name"));
                aliases.add(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aliases;
    }

    public int changeFollowers(Long siteId, int amount) {
        int status = 0;
        String path = "/api/sites/change/followers?siteId=" + siteId + "&amount=" + amount;
        System.out.println(apiUrl + port + path);
        try {
            HttpGet http = new HttpGet(apiUrl + port + path);
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            System.out.println(EntityUtils.toString(response.getEntity()));
            status = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int changeTariff(String tariff, Integer price, Integer debt, Integer discount) throws MalformedURLException {
        int status = 0;
        String path = "/api/sites/change/tariff";
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(apiUrl.split("//")[1].replace(":", "")).setPort(port).setPath(path);
        if (tariff != null) builder.setParameter("name", tariff);
        if (price != null) builder.setParameter("price", String.valueOf(price));
        if (debt != null) builder.setParameter("debt", String.valueOf(debt));
        if (discount != null) builder.setParameter("discount", String.valueOf(discount));
        System.out.println(builder.toString());
        try {
            HttpGet http = new HttpGet(builder.build());
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            System.out.println(EntityUtils.toString(response.getEntity()));
            status = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int executeBillingMethod(String name) {
        int status = 0;
        String path = "/api/sites/billingTest/" + name;
        System.out.println(apiUrl + directPort + path);
        try {
            HttpGet http = new HttpGet(apiUrl + directPort + path);
            http.addHeader("Accept", "application/json");
            http.addHeader("Connection", "Keep-Alive");
            http.addHeader("Content-Type", "application/json");
            http.addHeader("Referer", apiUrl + port + path);
            http.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(http);
            System.out.println(EntityUtils.toString(response.getEntity()));
            status = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public Long getTestSiteFollowersCount() {
        Long followers = 0L;
        String path = "/api/sites/" + testSiteId;
        try {
            HttpGet httpPost = new HttpGet(apiUrl + port + path);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Connection", "Keep-Alive");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Referer", apiUrl + port + path);
            httpPost.addHeader("Cookie", "accessToken=" + token);
            HttpResponse response = httpClient.execute(httpPost);
            JSONObject respObj = (JSONObject) parser.parse(EntityUtils.toString(response.getEntity()));
            followers = Long.valueOf((String) respObj.get("fa"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return followers;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTestSiteId() {
        return testSiteId;
    }

    public void setTestSiteId(long testSiteId) {
        this.testSiteId = testSiteId;
    }

    public String getApiUrl() {
        return apiUrl + port;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getHostUrl() {
        return port == 443 ? (hostUrl).substring(0,hostUrl.length()-1) : hostUrl + port;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

}
