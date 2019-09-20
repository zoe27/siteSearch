/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2019年9月20日 下午9:17:13
 * PathUtilTest.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.site.page.App;
import cn.com.site.page.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class PathUtilTest {

	@Test
	public void testPath() {
		System.out.println(PathUtil.resourcePath());
	}
}

