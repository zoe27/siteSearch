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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response) {
		// SearchResponse response =
		// esclient.getData(ESConstant.ES_SITE_INFO.INDEX.getInfo(),ESConstant.ES_SITE_INFO.TYPE.getInfo(),"图片");
		// return JSONObject.toJSONString(response);
		if(isMobile(request)) {
			return "m/index";
		}else {
			return "v1/index";
		}
	}

	@RequestMapping("/result")
	public String result(HttpServletRequest request, HttpServletResponse response) {
		if (isMobile(request)) {
			return "m/result";
		}else {
			return "v1/result";
		}
	}

	@RequestMapping("/searchIndex")
	public String search() {
		return "search";
	}

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		if(isMobile(request)) {
			return "m/index";
		}else {
			return "v1/index";
		}
	}

	/**
	 * 判断是否是移动端
	 * 
	 * @param request
	 * @return
	 */
	private boolean isMobile(HttpServletRequest request) {
		boolean isFromMobile = false;

		HttpSession session = request.getSession();
		// 检查是否已经记录访问方式（移动端或pc端）
		if (null == session.getAttribute("ua")) {
			try {
				// 获取ua，用来判断是否为移动端访问
				String userAgent = request.getHeader("USER-AGENT").toLowerCase();
				if (null == userAgent) {
					userAgent = "";
				}
				isFromMobile = PcOrMobile.isMobile(userAgent);
				// 判断是否为移动端访问
				/*if (isFromMobile) {
					session.setAttribute("ua", "mobile");
				} else {
					session.setAttribute("ua", "pc");
				}*/
			} catch (Exception e) {
				log.error("", e);
			}
		} else {
			isFromMobile = session.getAttribute("ua").equals("mobile");
		}
		log.info("The access if from mobile {}", isFromMobile);
		return isFromMobile;
	}
}
