package com.mobile.JSPath.bean;

import lombok.Data;
import util.AutoDocField;

import java.io.Serializable;


@Data
public class JSPathBean implements Serializable {

    @AutoDocField(note = "基础JSPath的JS，原JSPatch.js" , nullable = false)
    private String base;

    @AutoDocField(note = "修复的js文件")
    private String repair;

}
