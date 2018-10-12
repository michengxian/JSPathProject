package com.mobile.JSPath.base;


import util.AutoDocField;
import util.PropUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12360 on 2015/9/21.
 * API返回结果
 */
public class BaseResponse<T> extends BaseBean {
    private static final long serialVersionUID = -8883532584241597386L;
    @AutoDocField(note="seqNo值")
    private String seqNo;               //seqNo值
    @AutoDocField(note="是否请求成功")
    private Boolean isOk = true;        //是否请求成功
    @AutoDocField(note="返回数据")
    private T response;                //返回数据
    @AutoDocField(note="返回编码")
    private int code = 100;             //错误编码
    @AutoDocField(note="返回信息")
    private String desc = PropUtils.getProp(code);    //错误信息
    private Map<String, Object> otherAttribute = new HashMap<String, Object>();//其他额外参数
    public BaseResponse(String seqNo) {
        this.seqNo = seqNo;
    }

    public BaseResponse(String seqNo, int code) {
        this.code = code;
        this.seqNo = seqNo;
        this.desc = PropUtils.getProp(code);
    }

    public Boolean isOk() {
        return isOk;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Map<String, Object> getOtherAttribute() {
        return otherAttribute;
    }

    public void setOtherAttribute(Map<String, Object> otherAttribute) {
        this.otherAttribute = otherAttribute;
    }
}
