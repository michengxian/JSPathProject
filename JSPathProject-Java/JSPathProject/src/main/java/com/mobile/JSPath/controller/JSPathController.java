package com.mobile.JSPath.controller;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.mobile.JSPath.base.BaseResponse;
import com.mobile.JSPath.bean.JSPathBean;
import com.mobile.JSPath.service.JSPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/mobile/JSPath")
public class JSPathController {


    @Autowired
    private JSPathService service;


    @RequestMapping(value = "repair" ,method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponse<JSPathBean> repair(@RequestBody String req){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = format.format(now) + "" +now.getTime();
        BaseResponse<JSPathBean> response = new BaseResponse<JSPathBean>(time);

        response.setResponse(service.repair());

        return response;
    }
}
