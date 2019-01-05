package com.aglie.nju.traveltogetherapi.model;

import java.util.Map;

public class ResultModel {
    private int resCode;//返回码
    private String resMsg;//返回信息
    private Map<String, Object> data;//数据源

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
