package hust.shixun.grouptravel.entities;

import java.util.Date;

public class User {
    private int userId;
    private String openId;
    private Date creatTime;
    private Date lastVisitTime;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private int gender;
    private String nickname;
    private String phoneNum;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User(int userId, String openId, Date creatTime, Date lastVisitTime, String city, String province, String country, String avatarUrl, int gender, String nickname, String phoneNum) {
        this.userId = userId;
        this.openId = openId;
        this.creatTime = creatTime;
        this.lastVisitTime = lastVisitTime;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", openId='" + openId + '\'' +
                ", creatTime=" + creatTime +
                ", lastVisitTime=" + lastVisitTime +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
