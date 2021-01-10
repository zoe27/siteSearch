package cn.com.site.page.service.impl;

import cn.com.site.page.dto.SalaryCoreInfo;
import cn.com.site.page.dto.SalaryDto;
import cn.com.site.page.mapper.SalaryMapper;
import cn.com.site.page.security.Aes;
import cn.com.site.page.util.Md5Util;
import cn.com.site.page.vo.Salary;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.site.page.service.SalaryService;
import cn.com.site.page.util.FileLoad;
import cn.com.site.page.util.SalaryConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: SalaryJsonParse
 * @projectName PerfectSite
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-08-10 22:41
 */

@Component
public class SalaryJsonParse implements SalaryService{

	@Autowired
	private SalaryMapper salaryMapper;


	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileLoad fileLoad;

	public List<Map<String,String>> parseSalary() {
		String salaryPath = "salary/salaryInfo.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		array.forEach(e -> {
			Map<String, String> map = new HashedMap();
			JSONObject obj = JSON.parseObject(e.toString());
			String company = obj.getString(SalaryConstant.COMPANY);
			String title = obj.getString(SalaryConstant.TITLE);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = obj.getString(SalaryConstant.YEAR_OF_EXP);
			String yearInCome = obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString(SalaryConstant.BOUNS_COMP);
			String baseComp = obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = obj.getString(SalaryConstant.TOTAL_COMP);
			String baseOfMonth = obj.getString(SalaryConstant.BASE_MONTH_COMP);
			String stockComp = obj.getString(SalaryConstant.STOCK_COMP);
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString(SalaryConstant.HIRE_TYPE);
			String hours = obj.getString(SalaryConstant.HOURS);
			map.put(SalaryConstant.COMPANY, company);
			map.put(SalaryConstant.TITLE, title);
			map.put(SalaryConstant.LEVEL, level);
			map.put(SalaryConstant.YEAR_OF_EXP, yearOfExp);
			map.put(SalaryConstant.YEAR_IN_COME, yearInCome);
			map.put(SalaryConstant.BOUNS_COMP, bounsComp);
			map.put(SalaryConstant.BASE_COMP, baseComp);
			map.put(SalaryConstant.TOTAL_COMP, totalComp);
			map.put(SalaryConstant.STOCK_COMP, stockComp);
			map.put(SalaryConstant.DEGREE, degree);
			map.put(SalaryConstant.LOCATION, location);
			map.put(SalaryConstant.HIRE_TYPE, hireType);
			map.put(SalaryConstant.HOURS, hours);
			map.put(SalaryConstant.BASE_MONTH_COMP, baseOfMonth);
			list.add(map);
		});
		return list;
	}

	/*{
			"date":"2020-08-04",
			"baseMonthComp":34000,
			"country":"domestic",
			"jobCategory":"",
			"role":"",
			"gender":"男",
			"stockComp":0,
			"focus":[
				"后端"
    		],
			"industry":"IT",
			"title":"软件工程师",
			"yrOfExp":6,
			"bonusComp":102000,
			"offerType":"",
			"yrInCom":3,
			"company":"滴滴",
			"department":"滴滴快车",
			"baseComp":408000,
			"iat":1596547354,
			"curTimestamp":1596547357.997798,
			"timestamp":"2020-08-0413:22:37.997790",
			"hours":"",
			"level":"D7",
			"advancedSchool":"",
			"hireType":"社招",
			"degree":"本科",
			"bachelorSchool":"",
			"location":"北京",
			"comment":"每年涨薪比例很低",
			"_id":"5f29611dc488a530608ae313",
			"totalComp":510000,
			"bonusCompMulti":3,
			"baseMonthCompMulti":12
	}*/
	public void translateSalary(){
		String salaryPath = "salary/salaryInfo.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		array.forEach(e -> {
			JSONObject obj = JSON.parseObject(e.toString());
			String company = obj.getString(SalaryConstant.COMPANY);
			String title = obj.getString(SalaryConstant.TITLE);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = obj.getString(SalaryConstant.YEAR_OF_EXP);
			String yearInCome = obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString(SalaryConstant.BOUNS_COMP);
			String baseComp = obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = obj.getString(SalaryConstant.TOTAL_COMP);
			if (StringUtils.isEmpty(obj.getString(SalaryConstant.BASE_MONTH_COMP))) {
				String baseOfMonth = Float.parseFloat(baseComp) / 12 + "";
				obj.put(SalaryConstant.BASE_MONTH_COMP, baseOfMonth);
			}
			String baseOfMonth = String.format("￥%.1fk", Float.parseFloat(obj.getString(SalaryConstant.BASE_MONTH_COMP)) / 1000);
			String stockComp = obj.getString(SalaryConstant.STOCK_COMP);
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString(SalaryConstant.HIRE_TYPE);
			String hours = obj.getString(SalaryConstant.HOURS);

			salaryDeal(company, title, level, yearOfExp, yearInCome, bounsComp, baseComp, totalComp, baseOfMonth, stockComp, degree, location, hireType, hours);
		});
	}



	@Override
	public int saveSalary(SalaryDto salaryDto) {

		SalaryCoreInfo salaryCoreInfo = new SalaryCoreInfo();
		salaryCoreInfo.setBaseComp(salaryDto.getBaseComp());
		salaryCoreInfo.setBaseOfMont(salaryDto.getBaseOfMont());
		salaryCoreInfo.setBounsComp(salaryDto.getBounsComp());
		salaryCoreInfo.setStockComp(salaryDto.getStockComp());
		salaryCoreInfo.setTotalComp(salaryDto.getTotalComp());
		String salaryCoreInfoString = Aes.aesEncrypt(JSON.toJSONString(salaryCoreInfo));

		String md5Inpuf = String.format("%s%s%s%s%s%s%s%s", salaryDto.getCompany(), salaryDto.getTitle(), salaryDto.getLevel(),
				salaryDto.getYearOfExp(),salaryDto.getDegree(),salaryDto.getLocation(),salaryDto.getCollege(), salaryCoreInfo);
		String md5Info = Md5Util.MD51(md5Inpuf);

		Salary salary = new Salary(salaryDto.getCompany(), salaryDto.getTitle(),
				salaryDto.getLevel(), Float.parseFloat(salaryDto.getYearOfExp()),
				Float.parseFloat(StringUtils.isEmpty(salaryDto.getYearInCome()) ? "0" : salaryDto.getYearInCome()),
				0f,0f,0f,0f,0,
				salaryDto.getDegree(), salaryDto.getLocation(),
				"社招".equals(salaryDto.getHireType()) ? 0 : 1, salaryDto.getHours(),
				salaryDto.getCollege(), salaryCoreInfoString, md5Info);
		log.info("{}", salary);
		return salaryMapper.insert(salary);
	}

	@Override
	public List<Salary> selectByContion(String condition, Integer begin, Integer limit) {
		List<Salary> salaries = Lists.newArrayList();
		if (StringUtils.isEmpty(condition)){
			salaries = salaryMapper.selectAllPage(begin, limit);
		}else{
			salaries = salaryMapper.selectByCondition(condition, begin, limit);
		}
		// salaries = salaries.stream().collect(Collectors.toList());
		return salaries;
	}

	@Override
	public List<Salary> getAll() {
		return salaryMapper.selectAll();
	}

	@Override
	public List<String> getCompay() {
		List<Object> list =  salaryMapper.getCompany();
		List<String> listStr = list.stream().map(Object::toString).collect(Collectors.toList());
		return listStr;
	}


	/*{
		"totalyearlycompensation":"￥604000.0",
			"level":"16",
			"bonus":"2个月;",
			"ck":6155,
			"degree":"本科",
			"bk":-0.5,
			"base_month":"￥36.0k",
			"comp_id":3396,
			"yoe":"5年",
			"tags":"小米 北京 5年 16 小米北京 小米5年 小米16 本科5年 小米本科5年",
			"bu":"",
			"company":"小米",
			"location":"北京",
			"comp_type":"社招Offer",
			"pk":60.7,
			"position":"技术",
			"stock":"",
			"others":"每年有10w股票",
			"timestamp":"11/8/2020 17:26"
	}*/
	public void parseSalaryOffer(){
		String salaryPath = "salary/salary1.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		AtomicInteger i = new AtomicInteger(1);
		array.forEach(e -> {
			JSONObject obj = JSON.parseObject(e.toString());
			String companyName = decodeUnicode(obj.getString("ck"), obj.getString("company"));

			float tmp = Math.round(obj.getFloatValue("totalyearlycompensation") - obj.getFloatValue("pk"));
			String s = ("￥" + (1e3 * tmp)).toString();//.replace("/\B(?=(\d{3})+(?!\d))/g", ",");
			float tmp_r = (Math.round(obj.getFloatValue("base_month") - obj.getFloatValue("bk")) * 10) / 10;
			String r = tmp_r == 0 ? "-" : "￥" + tmp_r + "k";

			String tags = decodeUnicode(obj.getString("ck"), obj.getString("tags"));
			obj.replace("tags", tags);
			obj.replace("company", companyName);
			obj.replace("totalyearlycompensation", s);
			obj.replace("base_month", r);


			String company = companyName;
			String title = obj.getString(SalaryConstant.POSITION);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = StringUtils.isEmpty(obj.getString("yoe")) ? "0": obj.getString("yoe").replace("年", "");
			yearOfExp = StringUtils.equalsIgnoreCase("在校生", yearOfExp) ? "0" : yearOfExp;
			String yearInCome = "";// obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString("bonus");
			String baseComp = "";//obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = s ;//obj.getString("totalyearlycompensation");
			String baseOfMonth = r; //obj.getString("base_month");
			String stockComp = obj.getString("stock");
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString("comp_type");
			String hours = "";// obj.getString(SalaryConstant.HOURS);

			salaryDeal(company, title, level, yearOfExp, yearInCome, bounsComp, baseComp, totalComp, baseOfMonth, stockComp, degree, location, hireType, hours);
		});

	}

	private void salaryDeal(String company, String title, String level,
							String yearOfExp, String yearInCome, String bounsComp,
							String baseComp, String totalComp, String baseOfMonth,
							String stockComp, String degree, String location, String hireType, String hours) {
		try{
			SalaryCoreInfo coreInfo = new SalaryCoreInfo(bounsComp, baseComp, totalComp, baseOfMonth, stockComp);
			String salaryCoreInfo = Aes.aesEncrypt(JSON.toJSONString(coreInfo));

			String md5Inpuf = String.format("%s%s%s%s%s%s%s%s", company, title, level,
					yearOfExp,degree,location,"", salaryCoreInfo);
			String md5Info = Md5Util.MD51(md5Inpuf);

			Salary salary = new Salary(company, title, level, Float.parseFloat(yearOfExp),
					Float.parseFloat(StringUtils.isEmpty(yearInCome) ? "0" : yearInCome),
					0f, 0f, 0f, 0f, 0, degree, location,
					"社招".equals(hireType) ? 0 : 1, hours, "", salaryCoreInfo, md5Info);
			salaryMapper.insert(salary);
		}catch (Exception e){
			log.error("add date error, {}", e.getMessage());
		}

	}

	private String decodeUnicode(String key, String content) {
		char[] tc;
		tc = content.toCharArray();
		for (int i = 0; i < tc.length; i++) {
			int tmp = tc[i] - Integer.valueOf(key);
			if (tmp < 0) {
				tmp = tmp + 65536;
			}
			tc[i] = (char)tmp;
		}
		content = String.valueOf(tc);
		return content;
	}

}
