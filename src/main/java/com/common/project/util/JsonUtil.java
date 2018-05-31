package com.common.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.project.entity.CodeMessage;

public class JsonUtil {
public static JSONObject setSuccessJson(String msg){
    CodeMessage codeMessage=new CodeMessage();
    codeMessage.setCode("SUCCESS");
    codeMessage.setMsg(msg);
    return (JSONObject) JSON.toJSON(codeMessage);
}

    public static JSONObject setFailJson(String msg){
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode("Fail");
        codeMessage.setMsg(msg);
        return (JSONObject) JSON.toJSON(codeMessage);
    }
}
