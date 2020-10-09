/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2020年8月17日 下午10:24:34
 * SalaryService.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2020, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.service;

import cn.com.site.page.vo.Salary;

import java.util.List;

public interface SalaryService {

    int saveSalary(Salary salary);

    List<Salary> selectByContion(String condition, Integer begin, Integer limit);

}

