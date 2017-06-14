package com.zbw.springtest.model;

/**
 * Created by huangyin on 2016/10/28.
 */
public class VParams {
    private String ip;  // 访问者IP
    private int account = -1; // 访问者账号
    private String api; // 访问API
    private String devId;   // 设备唯一标识
    private String os;  // 操作系统及其版本
    private String version; // 当前软件版本
    private String session; // 访问者Session
    private String params;  // 参数
    private boolean isEncrypt = true; // 是否将params加密->"true":加密、"false":不加密
    private boolean isBase64 = true; // 是否将paramsBase64->"true" or "false"

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isEncrypt() {
        return isEncrypt;
    }

    public void setEncrypt(boolean encrypt) {
        isEncrypt = encrypt;
    }

    public boolean isBase64() {
        return isBase64;
    }

    public void setBase64(boolean base64) {
        isBase64 = base64;
    }
}
