package com.aglie.nju.traveltogetherapi.util;

import java.util.Map;
import com.aglie.nju.traveltogetherapi.model.ResultModel;

/**
 * 请求结果处理类
 */
public class ResultTools {
    /****
     * 返回码码记录：
     * 0--------success
     * 1001-----Parameter error
     * 1002-----Empty content
     * 1003-----Account exists
     * 1004-----Upload file is empty
     * 404------Exception throw error、failed
     *
     * @param resCode--返回码
     * @param resMsg---404服务器内部异常时提示消息(返回码不是404时传空即可)
     * @param map------数据源
     * @return
     */
    public static ResultModel result(int resCode, String resMsg, Map<String, Object> map) {
        ResultModel model = new ResultModel();
        model.setResCode(resCode);
        switch (resCode) {
            case 0:
                model.setResMsg("success");
                if (map != null) {
                    model.setData(map);
                }
                break;
            case 1001:
                model.setResMsg("Parameter error");
                break;
            case 1002:
                model.setResMsg("Empty content");
                break;
            case 1003:
                model.setResMsg("Account exists");
                break;
            case 1004:
                model.setResMsg("Upload file is empty");
                break;
            case 404:
                model.setResMsg(resMsg);
                break;
            default:
                model.setResMsg("Unknown error");
                break;
        }
        return model;
    }
}
