package com.zbw.springtest.model;

/**
 * 地址详情
 */
public class AddressDetail {

    private int id;
    private String userName;
    private String mobile;
    /** 所在地区 */
    private String area;
    /** 街道 */
    private String street;
    /** 详细地址 */
    private String detailAddress;
//    private boolean defaultAddress;


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
