package org.redrock.weixin.controller;

import org.redrock.weixin.annotation.Weixin;
import org.redrock.weixin.data.SnsapiBase;
import org.redrock.weixin.data.SnsapiUserInfo;
import org.redrock.weixin.interceptor.SnsapiBaseAuth;
import org.redrock.weixin.interceptor.SnsapiUserInfoAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller("IndexController")
public class IndexController {

    @Weixin(SnsapiBaseAuth.class)
    @RequestMapping("/index.html")
    public void index(HttpServletRequest request,
                      HttpServletResponse response,
                      SnsapiBase data) throws Exception {
        PrintWriter writer = response.getWriter();
        System.out.println(data.getAccessToken());
        writer.println(data.getAccessToken());
        writer.println(data.getOpenid());
        writer.println(data.getRefreshToken());
        writer.println(data.getExpireIn());
    }

    @Weixin(SnsapiUserInfoAuth.class)
    @RequestMapping("/test.html")
    public void test(SnsapiUserInfo data,
                     HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(data.getHeadImgUrl());
    }
}
