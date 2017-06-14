package com.zbw.springtest.model;


public class AddressList {
    private int id;
    private String userName;
    private String mobile;
    private String detailAddress;
    /** 是否为默认地址 */
    private boolean defaultAddress;
    /** 地址详情Id */
    private int detailAddressId;
    /** 用户Id */
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public int getDetailAddressId() {
        return detailAddressId;
    }

    public void setDetailAddressId(int detailAddressId) {
        this.detailAddressId = detailAddressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
