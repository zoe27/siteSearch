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
import javax.servlet.http.HttpServletResponse;

import cn.com.site.page.dto.SalaryCoreInfo;
import cn.com.site.page.dto.SalaryDto;
import cn.com.site.page.security.Aes;
import cn.com.site.page.util.AccessSourceLog;
import cn.com.site.page.util.PcOrMobile;
import cn.com.site.page.vo.Salary;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.com.site.page.dto.PageBean;
import cn.com.site.page.service.impl.SalaryJsonParse;

@Controller
public class SalaryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

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

	@RequestMapping("/salary/addIdx")
	public String addSalary(Model model){
		model.addAttribute("salaryDto", new SalaryDto());
		return "salary/add";
	}

	@PostMapping("salary/add")
	@ResponseBody
	public String addSalaryInfo(@ModelAttribute SalaryDto salaryDto){
		log.info("{}", salaryDto);
		Salary salary = new Salary();
		BeanUtils.copyProperties(salaryDto, salary);
		salary.setBaseOfMonth(Float.parseFloat(salaryDto.getBaseOfMont()));
		salary.setBounsComp(0f);//Float.parseFloat(salaryDto.getBaseComp()));
		salary.setHireType(0);
		salary.setStockComp(Integer.parseInt(salaryDto.getStockComp()));
		salary.setTotalComp(Float.parseFloat(salaryDto.getTotalComp()));
		salary.setYearInCome(Float.parseFloat(salaryDto.getYearInCome()));
		salary.setYearOfExp(Float.parseFloat(salaryDto.getYearOfExp()));
		SalaryCoreInfo salaryCoreInfo = new SalaryCoreInfo();
		String salaryCoreInfoString = Aes.aesEncrypt(JSON.toJSONString(salaryCoreInfo));
		salary.setCoreInfo(salaryCoreInfoString);
		log.info("{}", salary);
		salaryJsonParse.saveSalary(salary);
		return "result";
	}

	@RequestMapping("salary/translate")
	@ResponseBody
	public String translateSalary(@ModelAttribute SalaryDto salaryDto){
		salaryJsonParse.translateSalary();
		return "finish tranlate";
	}


	@RequestMapping("salary/salaryCondtion")
	@ResponseBody
	public PageBean<List<Salary>> getSalaryCondition(HttpServletRequest request,
													 @RequestParam(name = "begin", defaultValue = "0") Integer begin,
													 @RequestParam(name = "limit", defaultValue = "10") Integer limit,
													 @RequestParam(name = "query", defaultValue = "") String query){

		log.info("recive a request, params is : query = {}, begin = {}, limit = {}", query, begin, limit);
		List<Salary> list = Lists.newArrayList();
		int idx = begin * limit;
		list = salaryJsonParse.selectByContion(query, idx, limit);
		PageBean<List<Salary>> pageBean = new PageBean<>();
		pageBean.setBegin(begin);
		pageBean.setSize(limit);
		pageBean.setT(list);
		return pageBean;
	}

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		AccessSourceLog.accessLog(request);
		if (PcOrMobile.isMobile(request)) {
			return "salary/index";
		} else {
			return "salary/index";
		}
	}

}
