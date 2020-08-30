/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2020年8月17日 下午10:20:43
 * SalaryController.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2020, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.site.page.dto.PageBean;
import cn.com.site.page.service.impl.SalaryJsonParse;

@Controller
public class SalaryController {
	
	@Autowired
	private SalaryJsonParse salaryJsonParse;

	@RequestMapping("/getSalary")
	@ResponseBody
	public PageBean<List<Map<String, String>>> getSalary(HttpServletRequest request,
			@RequestParam(name = "begin", defaultValue = "0") Integer begin,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
		List<Map<String, String>> list = salaryJsonParse.parseSalary();
		Collections.reverse(list);
		int idx = (begin + 1) * limit;
		list = list.size() > idx ? list.subList(idx - limit, idx) : list.subList(idx - limit, list.size() );
		PageBean<List<Map<String, String>>> pageBean = new PageBean<List<Map<String, String>>>();
		pageBean.setBegin(begin);
		pageBean.setSize(limit);
		pageBean.setT(list);
		return pageBean;
	}
	
	@RequestMapping("/salary/index")
	public String salaryIndex(HttpServletRequest request) {
		return "salary/index";
	}

}
