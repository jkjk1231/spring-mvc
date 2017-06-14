package com.zbw.springtest.model;

/**
 * 主要用于Service层向Controller传递数据
 */
public class MediumResult<T> {
    /** 操作成功与否的标记 */
    private boolean flag;
    /** 操作结果描述 */
    private String desc;
    /** 操作结果 */
    private T info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
