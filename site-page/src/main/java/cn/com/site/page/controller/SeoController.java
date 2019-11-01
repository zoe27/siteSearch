/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2019年10月15日 上午12:04:26
 * SeoController.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeoController {
	
	@RequestMapping("/sitemap.xml")
	public String search() {
		return "sitemap";
	}

}

