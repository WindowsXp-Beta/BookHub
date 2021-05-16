package com.windowsxp.bookstore.entity;

public class User {
    private Integer userId;

    private String userName;
    private String password;

    private Integer userType;
    private String nickName;
    private String tel;
    private String address;

    public User(Integer userId, String userName, String password, Integer userType, String nickName, String tel, String address) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.nickName = nickName;
        this.tel = tel;
        this.address = address;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public Integer getUserType() {
        return this.userType;
    }

}
