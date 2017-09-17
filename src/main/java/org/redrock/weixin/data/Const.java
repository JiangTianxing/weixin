package org.redrock.weixin.data;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Const {

    private static String appId;

    private static String appSecret;

    private static String token;

    static {
        InputStream in = Const.class.getClassLoader().getResourceAsStream("weixin.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            appId = properties.getProperty("weixin.appID");
            appSecret = properties.getProperty("weixin.appsecret");
            token = properties.getProperty("weixin.Token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getToken() {
        return token;
    }

    public static String getAccessToken() throws IOException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String access_token = null;
        if (!jedis.exists("access_token")) {
            synchronized (Const.class) {
                if (!jedis.exists("access_token")) {
                    access_token = curlForAccessToken();
                    jedis.set("access_token", access_token);
                    int expire_time = (int)(System.currentTimeMillis() / 1000) + 6000;
                    jedis.expire("access_token", expire_time);
                }
            }
        } else {
            access_token = jedis.get("access_token");
        }
        return access_token;
    }

    private static String curlForAccessToken() throws IOException {
        StringBuilder temp = new StringBuilder();
        temp.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
                .append(appId).append("&secret=").append(appSecret);
        System.out.println(temp.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(temp.toString())
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        JSONObject object = JSONObject.parseObject(json);
        String access_token = object.getString("access_token");
        return access_token;
    }

}
