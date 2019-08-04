package cn.com.site.page.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.com.site.page.dto.SiteResDto;
import cn.com.site.page.service.SearchService;

/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年8月4日 上午10:15:39
 * SearchControl.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
@Controller
public class SearchControl {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	@ResponseBody
	public List<SiteResDto> searchByQuery(@RequestParam("query") String query,
								@RequestParam(name = "begin", defaultValue = "0") Integer begin,
								@RequestParam(name = "limit", defaultValue = "6") Integer limit) {
		log.info("recive a requesr, params is : query = {}, begin = {}, limit = {}", query, begin, limit);
		List<SiteResDto> list = searchService.searchByQuery(query, begin, limit);
		return list;
	}
	
	@RequestMapping("/target")
	public void sendToDest(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		log.info("send to dest is : {}", url);
		response.sendRedirect(url);
		// 下面代码仅做保留，提供第二中国跳转方式。
		//model.addAttribute("url", url);
		//return "/common/redirect";
	}
}

