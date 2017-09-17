package org.redrock.weixin.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SnsapiUserInfo {
    private String openId;
    private String nickName;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private List<String> privilege;
    private String unionid;

    public SnsapiUserInfo() {}

    public SnsapiUserInfo(JSONObject data) {
        this.openId = data.getString("openid");
        this.nickName = data.getString("nickname");
        this.sex = data.getString("sex");
        this.province = data.getString("province");
        this.headImgUrl = data.getString("headimgurl");
        this.unionid = data.getString("unionid");
        this.privilege = new ArrayList<>();
        JSONArray privilege = data.getJSONArray("privilege");
        for (int i = 0; i < privilege.size(); i++) {
            this.privilege.add(privilege.getString(i));
        }
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
