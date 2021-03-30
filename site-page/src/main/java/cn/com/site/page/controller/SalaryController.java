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

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.site.page.dto.*;
import cn.com.site.page.security.Aes;
import cn.com.site.page.service.CommentService;
import cn.com.site.page.service.EvaluateLevelMappingService;
import cn.com.site.page.util.*;
import cn.com.site.page.vo.Comment;
import cn.com.site.page.vo.EvaluateComp;
import cn.com.site.page.vo.Salary;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
@CrossOrigin
public class SalaryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SalaryJsonParse salaryJsonParse;

	@Autowired
	private CommentService commentService;

	@Autowired
	private EvaluateLevelMappingService evaluateLevelMappingService;

	@RequestMapping("/getSalary")
	@ResponseBody
	public PageBean<List<Map<String, String>>> getSalary(HttpServletRequest request,
			@RequestParam(name = "begin", defaultValue = "0") Integer begin,
			@RequestParam(name = "limit", defaultValue = "10000") Integer limit) {
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

	/**
	 * redirect to the page
	 * @param model
	 * @return
	 */
	@RequestMapping("/salary/addIdx")
	public String addSalary(Model model){
		model.addAttribute("salaryDto", new SalaryDto());
		return "salary/add";
	}

	/**
	 * add salary
	 * @param salaryDto
	 * @return
	 */
	@PostMapping("salary/add")
	@ResponseBody
	public String addSalaryInfo(@RequestBody SalaryDto salaryDto){
		log.info("{}", salaryDto);

		salaryJsonParse.saveSalary(salaryDto);
		return "result";
	}

	/**
	 * translate json to mysql db
	 * @param salaryDto
	 * @return
	 */
	@RequestMapping("salary/translate")
	@ResponseBody
	public String translateSalary(@ModelAttribute SalaryDto salaryDto){
		salaryJsonParse.translateSalary();
		salaryJsonParse.parseSalaryOffer();
		return "finish tranlate";
	}


	/**
	 * search salary in different conditions
	 * @param request
	 * @param begin
	 * @param limit
	 * @param query
	 * @return
	 */
	@RequestMapping("salary/salaryCondtion")
	@ResponseBody
	public PageBean<List<Salary>> getSalaryCondition(HttpServletRequest request,
													 @RequestParam(name = "begin", defaultValue = "0") Integer begin,
													 @RequestParam(name = "limit", defaultValue = "100") Integer limit,
													 @RequestParam(name = "query", defaultValue = "") String query){

		log.info("recive a request, params is : query = {}, begin = {}, limit = {}", query, begin, limit);
		List<Salary> list = Lists.newArrayList();
		int idx = begin * limit;
		list = salaryJsonParse.selectByContion(query, idx, limit);
		int total = 0;
		List listCnt = salaryJsonParse.selectByContion(query, 0, Integer.MAX_VALUE);
		total = listCnt.size();
		PageBean<List<Salary>> pageBean = new PageBean<>();
		pageBean.setBegin(begin);
		pageBean.setSize(limit);
		pageBean.setTotal(total);
		pageBean.setT(list);
		return pageBean;
	}

	/**
	 * redirect to salary index page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/")
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AccessSourceLog.accessLog(request);
		response.sendRedirect("index.html");
		/*AccessSourceLog.accessLog(request);
		if (PcOrMobile.isMobile(request)) {
			return "salary/index";
		} else {
			return "salary/index";
		}*/
	}

	/**
	 * redirect to level page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/level")
	public String levelIdx(HttpServletRequest request, HttpServletResponse response) {
		AccessSourceLog.accessLog(request);
		if (PcOrMobile.isMobile(request)) {
			return "salary/level";
		} else {
			return "salary/level";
		}
	}

	/**
	 * get all companies
	 * @return
	 */
	@RequestMapping("/getCompany")
	@ResponseBody
	public List<String> getCompany(){
		return salaryJsonParse.getCompay();//.subList(0,30);
	}

	/**
	 * deprecated
	 * get all level with corresponding salary
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

	/**
	 * add comment for some salary
	 * @return
	 */
	@PostMapping("/salary/add/comment")
	@ResponseBody
	public ResponseSalary addComment(@RequestParam("comment") Comment comment,
							HttpServletRequest request){
		String ip = IpUtils.getIpAdrress(request);
		comment.setIp(ip);
		Comment commentRes = commentService.addComment(comment);
		ResponseSalary responseSalary = new ResponseSalary();
		if (null == commentRes){
			responseSalary.setStatus(503);
			responseSalary.setMsg("add error");
			return responseSalary;
		}
		responseSalary.setT(commentRes);
		return responseSalary;
	}

	/**
	 * get salary comments
	 * @param salaryId
	 * @return
	 */
	@RequestMapping("/salary/getComments")
	@ResponseBody
	public ResponseSalary getComments(@RequestParam("salaryId") String salaryId){
		ResponseSalary responseSalary = new ResponseSalary();
		try {
			Integer salaryIdReal = Integer.parseInt(salaryId);
			List<Comment> list = commentService.getCommentBySalaryId(salaryIdReal);
			responseSalary.setStatus(200);
			responseSalary.setT(list);
		} catch (Exception e) {
			responseSalary.setStatus(500);
			responseSalary.setMsg("salary id is error");
		}
		return responseSalary;
	}

	@PostMapping("/salary/addlevel")
	@ResponseBody
	public ResponseSalary addCompanyLevel(@RequestBody List<String> rankList){
		ResponseSalary responseSalary = new ResponseSalary();
		try{
			EvaluateComp evaluateComp = new EvaluateComp();
			String companyName = JSON.parseObject(JSON.parseArray(rankList.get(0)).getString(0)).getString("name");
			// 0 : level info
			// 1 : height info
			String content = String.format("%s$$%s", rankList.get(0), rankList.get(1));

			evaluateComp.setCompanyName(companyName);
			evaluateComp.setLevelMapping(content);
			evaluateComp.setCompanyId(0);
			evaluateLevelMappingService.addEvaluateComp(evaluateComp);
			responseSalary.setT(evaluateComp);
			responseSalary.setStatus(200);
		}catch (Exception e){
			responseSalary.setMsg("something is wrong");
			responseSalary.setStatus(500);
		}
		/*evaluateComp = evaluateLevelMappingService.addEvaluateComp(evaluateComp);
		if (evaluateComp == null){
			responseSalary.setMsg("something is wrong");
			responseSalary.setStatus(500);
		}else{
			responseSalary.setT(evaluateComp);
			responseSalary.setStatus(200);
		}*/
		log.info("{}", rankList);
		return responseSalary;

	}

	@RequestMapping("/salary/mappingLevel")
	@ResponseBody
	public ResponseSalary getLevelMapping(@RequestParam(value = "companyName" , defaultValue = "") String companyName,
											  @RequestParam(name = "companyId", defaultValue = "0") String companyId){
		List<EvaluateComp> list = Lists.newArrayList();
		ResponseSalary responseSalary = new ResponseSalary();
		String dataPre = "contrast_data";
		Map<String, Object> map = new HashMap<>();
		try{
			list = evaluateLevelMappingService.getEvaluateComp(companyName, Integer.parseInt(companyId));
			for (int i = 0; i < list.size(); i++) {
				EvaluateComp evaluateComp = list.get(i);
				String companyInfoKey = String.format("%s%d", dataPre, i+1);
				Map<String, String> singleCompany = new HashMap<>();
				String[] content = evaluateComp.getLevelMapping().split("\\$\\$");
				String levelInfos = content[0];
				String heightInfo = content[1];
				singleCompany.put("companyName", evaluateComp.getCompanyName());
				/**
				 * 需要把公司名称去掉
				 */
				// '[{"companyName":"","name":"百度"},{"level":"T3","extends":"","salaryRange":"20W-30W"},
				// {"level":"T4","extends":"","salaryRange":"25W-40W"},{"level":"T5","extends":"","salaryRange":"30W-50W"},
				// {"level":"T6","extends":"","salaryRange":"40W-60W"},{"level":"","extends":"","salaryRange":""},
				// {"level":"","extends":"","salaryRange":""}]'

				JSONArray levelInfoJson = JSON.parseArray(levelInfos);
				JSONArray heightInfoJson = JSON.parseArray(heightInfo);
				levelInfos = JSON.toJSONString(levelInfoJson.stream().filter(e ->StringUtils.isNotEmpty(JSON.parseObject(e.toString()).getString("level"))).collect(Collectors.toList()));
				heightInfo = JSON.toJSONString(heightInfoJson.stream().filter(e -> StringUtils.isNotEmpty(JSON.parseObject(e.toString()).getString("height"))).collect(Collectors.toList()));
				// levelInfos = String.format("%s%s", "[", levelInfos.substring(levelInfos.indexOf("},{") + 2, levelInfos.length()));
				singleCompany.put("data", levelInfos);
				singleCompany.put("height", heightInfo);
				map.put(companyInfoKey, singleCompany);
			}
			responseSalary.setT(map);
			responseSalary.setStatus(200);
		}catch (Exception e){
			responseSalary.setStatus(500);
			responseSalary.setMsg("error");
		}
		return responseSalary;
	}

	@PostMapping("/salary/evaluatemappingLevel")
	@ResponseBody
	public ResponseSalary evaluateLevelMapping(@RequestParam("levelMapping") LevelMapping levelMapping){
		List<LevelMapping> levelMappings = Lists.newArrayList();
		ResponseSalary responseSalary = new ResponseSalary();
		try {
			// compare all company
			levelMappings = evaluateLevelMappingService.evaluateLevel(levelMapping);
			responseSalary.setT(levelMappings);
			responseSalary.setStatus(200);
		} catch (Exception e) {
			responseSalary.setStatus(500);
			responseSalary.setMsg("error");
		}
		return responseSalary;
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
