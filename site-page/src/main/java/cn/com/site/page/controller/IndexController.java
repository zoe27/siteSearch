/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年7月28日 下午9:35:49
 * IndexController.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.controller;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.site.es.esUtil.ESClient;
import cn.com.site.es.util.ESConstant;

@Controller
public class IndexController {
	
	@Autowired
	private ESClient esclient;

	@RequestMapping("/index")
	public String toIndex() {
		SearchResponse response = esclient.getData(ESConstant.ES_SITE_INFO.INDEX.getInfo(),ESConstant.ES_SITE_INFO.TYPE.getInfo(),"图片");
		//return JSONObject.toJSONString(response);
		return "index";
	}
	
	@RequestMapping("/result")
	public String result() {
		return "result";
	}
	
	@RequestMapping("/searchIndex")
	public String search() {
		return "search";
	}
	
	@RequestMapping("/")
	public String index() {
		return "search";
	}
}

