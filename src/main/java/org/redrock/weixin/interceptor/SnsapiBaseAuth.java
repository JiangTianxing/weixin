package org.redrock.weixin.interceptor;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.redrock.weixin.data.Const;
import org.redrock.weixin.data.SnsapiBase;
import org.redrock.weixin.util.RequestUtil;
import org.redrock.weixin.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class SnsapiBaseAuth implements BaseInterceptor {

    private String appId;

    private String appSecret;

    private String key;

    public SnsapiBaseAuth() {
        appId = Const.getAppId();
        appSecret = Const.getAppSecret();
        key = SnsapiBase.class.toString();
    }

    @Override
    public boolean interceptor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (httpServletRequest.getSession().getAttribute(key) != null) {
            return true;
        }
        String code = RequestUtil.getParameter(httpServletRequest, "code");
        if (!StringUtil.isBlank(code)) {
            StringBuilder temp = new StringBuilder();
            temp.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                    .append(appId).append("&secret=")
                    .append(appSecret).append("&code=")
                    .append(code).append("&grant_type=authorization_code");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(temp.toString())
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject data = JSONObject.parseObject(json);
            if (!StringUtil.isBlank(data.getString("access_token"))) {
                SnsapiBase baseData = new SnsapiBase(data);
                httpServletRequest.getSession().setAttribute(key, baseData);
                httpServletRequest.setAttribute(key, baseData);
                return true;
            }
        }
        StringBuilder temp = new StringBuilder();
        String redirectUri = URLEncoder.encode("http://jx3536.s1.natapp.link/index.html", "UTF-8");
        temp.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(appId)
                .append("&redirect_uri=").append(redirectUri)
                .append("&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        httpServletResponse.sendRedirect(temp.toString());
        return false;
    }
}