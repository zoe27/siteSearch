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

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.site.page.dto.*;
import cn.com.site.page.security.Aes;
import cn.com.site.page.util.AccessSourceLog;
import cn.com.site.page.util.ColorUtil;
import cn.com.site.page.util.PcOrMobile;
import cn.com.site.page.util.SalaryConstant;
import cn.com.site.page.vo.Salary;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

		salaryJsonParse.saveSalary(salaryDto);
		return "result";
	}

	@RequestMapping("salary/translate")
	@ResponseBody
	public String translateSalary(@ModelAttribute SalaryDto salaryDto){
		salaryJsonParse.translateSalary();
		salaryJsonParse.parseSalaryOffer();
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

	@RequestMapping("/level")
	public String levelIdx(HttpServletRequest request, HttpServletResponse response) {
		AccessSourceLog.accessLog(request);
		if (PcOrMobile.isMobile(request)) {
			return "salary/level";
		} else {
			return "salary/level";
		}
	}

	@RequestMapping("/getCompany")
	@ResponseBody
	public List<String> getCompany(){
		return salaryJsonParse.getCompay();
	}

	/**
	 * deprecated
	 * @param company
	 * @return
	 */
	@RequestMapping("/salary/level")
	@ResponseBody
	public LevelDto getSalaryLevel(@RequestParam("company") String company){

		// get max and min salary
		List<Salary> list = salaryJsonParse.getAll();
		list.forEach(e->{
			e.setTotalComp(JSONObject.parseObject(Aes.aesDecrypt(e.getCoreInfo())).getFloatValue(SalaryConstant.TOTAL_COMP));
		});
		double maxSalary = list.stream().mapToDouble(e -> e.getTotalComp().doubleValue()).max().orElse(1D);
		double minSalary = list.stream().mapToDouble(e -> e.getTotalComp().doubleValue()).min().orElse(1D);

		String color = ColorUtil.randomColor();

		// get certain company data
		List<Salary> salaryList = salaryJsonParse.selectByContion(company, 0, Integer.MAX_VALUE);
		LevelDto levelDto = new LevelDto();
		levelDto.setCompany(company);

		Map<String, Object> listLevel = salaryList.stream().collect(Collectors.toMap(Salary::getLevel,
					salary -> salaryList.stream().filter(e-> StringUtils.equalsIgnoreCase(salary.getLevel(), e.getLevel()))
							.map(e->Float.parseFloat(JSON.parseObject(Aes.aesDecrypt(e.getCoreInfo()), SalaryCoreInfo.class).getTotalComp()))
							.sorted(Comparator.naturalOrder())
							.collect(Collectors.toList()),
				(oldValue, newValue) -> newValue,   // 如果有相同的key,使用新key
				LinkedHashMap::new));   // 返回ListedHashMap,保持有序));

		Map<String, LevelSalaryDto> listLevelResult = Maps.newHashMap();
		listLevel.forEach((k,v)->{
			listLevelResult.put(k, transToResultStruct( k, (List) v));
		});

		List<LevelSalaryDto> listres = listLevelResult.values().stream().collect(Collectors.toList()).stream()
				.sorted(Comparator.comparing(LevelSalaryDto::getAverage)).collect(Collectors.toList());

		double min = listres.stream().mapToDouble(e -> e.getLow()).min().orElse(0D);
		double max = listres.stream().mapToDouble(e -> e.getHigh()).max().orElse(0D);

		//levelDto.setMap(listLevelResult);
		levelDto.setO(listres);
		/*levelDto.setMax(max);
		levelDto.setMin(min);
		levelDto.setGap((int)(max - min));
		levelDto.setCompany(ColorUtil.randomColor());*/

		double gapCompany = max - minSalary;
		int tableHeigh = (int)( gapCompany / (maxSalary - minSalary) * SalaryConstant.HEIGHT);
		levelDto.setTableHeight(tableHeigh);
		AtomicReference<Double> levelbelow = new AtomicReference<>(minSalary);
		listres.forEach(e->{
			double gapLevel = e.getHigh() - e.getLow() == 0 ? e.getHigh() - levelbelow.get() : e.getHigh() - e.getLow() ;

			e.setHeight(String.format("%.4f%s", (e.getHigh() - levelbelow.get()) * 1.0/ gapCompany * tableHeigh, "px"));
			levelbelow.set(e.getHigh());
			e.setColor(color.concat(",").concat(String.format("%.2f", e.getHigh()/maxSalary)));
		});

		if (min != minSalary){
			LevelSalaryDto levelSalaryDto = new LevelSalaryDto();
			levelSalaryDto.setColor("255,255,255");
			levelSalaryDto.setHeight(String.format("%.4f%s", (min - minSalary) / gapCompany * tableHeigh, "px"));
			levelSalaryDto.setHigh(0d);
			listres.add(0, levelSalaryDto);
		}

		levelDto.setMax(max);
		levelDto.setMin(min);
		levelDto.setGap((int) gapCompany);
		levelDto.setColor(String.valueOf((maxSalary - minSalary) ));
		return levelDto;
	}

	private LevelSalaryDto transToResultStruct(String level, List v) {
		Double max =
				v.stream()
						.mapToDouble(e->
								Double.parseDouble(e.toString())
						).max().orElse(0D);
		Double min = v.stream().mapToDouble(e-> Double.parseDouble(e.toString())).min().orElse(0D);
		Double avg = v.stream().mapToDouble(e -> Double.parseDouble(e.toString())).average().orElse(0D);
		Double median = Double.parseDouble(v.get(v.size() / 2).toString());
		LevelSalaryDto levelSalaryDto = new LevelSalaryDto(level, max, min, median, avg);
		return levelSalaryDto;
	}

}
