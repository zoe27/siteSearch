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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}
}

