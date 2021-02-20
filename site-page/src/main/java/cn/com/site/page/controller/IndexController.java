/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年7月28日 下午9:35:49
 * IndexController.java
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @since JDK 1.8
 */
package cn.com.site.page.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.site.page.util.AccessSourceLog;
import cn.com.site.page.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.site.es.esUtil.ESClient;
import cn.com.site.page.util.PcOrMobile;

@Controller
public class IndexController {

    @Autowired
    private ESClient esclient;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //@RequestMapping("/index")
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        // SearchResponse response =
        // esclient.getData(ESConstant.ES_SITE_INFO.INDEX.getInfo(),ESConstant.ES_SITE_INFO.TYPE.getInfo(),"图片");
        // return JSONObject.toJSONString(response);
        AccessSourceLog.accessLog(request);
        if (PcOrMobile.isMobile(request)) {
            return "m/index";
        } else {
            return "v1/index";
        }
    }

    @RequestMapping("/result")
    public String result(HttpServletRequest request, HttpServletResponse response) {
        AccessSourceLog.accessLog(request);
        if (PcOrMobile.isMobile(request)) {
            return "m/result";
        } else {
            return "v1/result";
        }
    }

    @RequestMapping("/searchIndex")
    public String search() {
        return "search";
    }

    /*@RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        AccessSourceLog.accessLog(request);
        if (PcOrMobile.isMobile(request)) {
            return "m/index";
        } else {
            return "v1/index";
        }
    }*/


}
