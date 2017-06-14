package com.zbw.springtest.model;

import java.util.HashMap;

/**
 * Created by chenjun on 2016/10/28.
 */
public class VResult<Data> {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 0;
    /**
     * 查询无结果
     */
    public static final int SUCCESS_NO_RESULT = 1;
    /**
     * 登陆已超时
     */
    public static final int FAIL_LOGIN_TIME_OUT = 2;
    /**
     * 客户端参数错误
     */
    public static final int FAIL_PARAM_ERROR = 3;
    /**
     * 服务器内部错误
     */
    public static final int FAIL_SERVER_ERROR = 4;
    /**
     * 客户端版本过低
     */
    public static final int FAIL_VERSION_TOO_LOW = 5;
    /**
     * 服务器无此方法
     */
    public static final int FAIL_NO_API = 6;
    /**
     * 业务失败
     */
    public static final int FAIL_BUSINESS = 7;

    private int status; // 状态，0为成功
    private String desc;    // 状态描述
    private Data info;  // 具体Object
    private boolean encrypt;    // 是否加密

    /**
     * 标识符status对应的描述
     */
    public static final HashMap<Integer, String> DESC_MAPPING;

    static {
        DESC_MAPPING = new HashMap<Integer, String>();
        DESC_MAPPING.put(SUCCESS, "操作成功");
        DESC_MAPPING.put(SUCCESS_NO_RESULT, "查询无结果");
        DESC_MAPPING.put(FAIL_LOGIN_TIME_OUT, "登陆已超时");
        DESC_MAPPING.put(FAIL_PARAM_ERROR, "客户端参数错误");
        DESC_MAPPING.put(FAIL_SERVER_ERROR, "服务器内部错误");
        DESC_MAPPING.put(FAIL_VERSION_TOO_LOW, "客户端版本过低,请更新最新版本");
        DESC_MAPPING.put(FAIL_NO_API, "没有该API");
        DESC_MAPPING.put(FAIL_BUSINESS, "业务失败");
    }

    public VResult() {}

    public VResult(int status) {
        setStatus(status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        this.desc = DESC_MAPPING.get(status);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Data getInfo() {
        return info;
    }

    public void setInfo(Data info) {
        this.info = info;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }
}
