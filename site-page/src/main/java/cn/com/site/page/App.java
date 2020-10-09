/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年7月28日 下午9:29:20
 * App.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主入口函数
 */
@SpringBootApplication
@ComponentScan("cn.com.site")
@MapperScan("cn.com.site.page.mapper")
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(App.class, args);
	}

}

