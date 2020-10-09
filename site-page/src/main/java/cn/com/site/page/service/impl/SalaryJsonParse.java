package cn.com.site.page.service.impl;

import cn.com.site.page.mapper.SalaryMapper;
import cn.com.site.page.vo.Salary;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.site.page.service.SalaryService;
import cn.com.site.page.util.FileLoad;
import cn.com.site.page.util.SalaryConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	// {"date":"2020-08-04","baseMonthComp":34000,"country":"domestic","jobCategory":"","role":"","gender":"男","stockComp":0,"focus":["后端"],
	// "industry":"IT","title":"软件工程师","yrOfExp":6,"bonusComp":102000,"offerType":"","yrInCom":3,"company":"滴滴","department":"滴滴快车","baseComp":408000,
	// "iat":1596547354,"curTimestamp":1596547357.997798,"timestamp":"2020-08-04
	// 13:22:37.997790","hours":"","level":"D7","advancedSchool":"","hireType":"社招","degree":"本科",
	// "bachelorSchool":"","location":"北京","comment":"每年涨薪比例很低","_id":"5f29611dc488a530608ae313","totalComp":510000,"bonusCompMulti":3,"baseMonthCompMulti":12}

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

	public void translateSalary(){
		String salaryPath = "salary/salaryInfo.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		array.forEach(e->{
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
			Salary salary = new Salary(company, title, level, Float.parseFloat(yearOfExp),
					Float.parseFloat(StringUtils.isEmpty(yearInCome) ? "0" : yearInCome),
					Float.parseFloat(StringUtils.isEmpty(bounsComp) ? "0" : bounsComp),
					Float.parseFloat(StringUtils.isEmpty(baseComp) ? "0" : baseComp),
					Float.parseFloat(StringUtils.isEmpty(totalComp) ? "0" : totalComp),
					Float.parseFloat(StringUtils.isEmpty(baseOfMonth) ? "0" : baseOfMonth),
					Integer.parseInt(StringUtils.isEmpty(stockComp) ? "0" : stockComp), degree, location,
					"社招".equals(hireType) ? 0 : 1, hours, "");
			salaryMapper.insert(salary);
		});
	}



	@Override
	public int saveSalary(Salary salary) {
		salaryMapper.insert(salary);
		return 0;
	}

	@Override
	public List<Salary> selectByContion(String condition, Integer begin, Integer limit) {
		if (StringUtils.isEmpty(condition)){
			return salaryMapper.selectAllPage(begin, limit);
		}else{
			return salaryMapper.selectByCondition(condition, begin, limit);
		}
	}
}
