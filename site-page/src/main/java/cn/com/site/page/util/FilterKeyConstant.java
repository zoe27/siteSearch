/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2020年1月6日 下午9:47:45
 * FilterKeyConstant.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2020, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.util;

/**
 * 对于某些关键字，需要过滤掉
 */
public class FilterKeyConstant {
	
	public enum FILTER_KEY{
		PAGE_NON_EXIST("页面不存在");
		
		private String info;

        private FILTER_KEY(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
	}

}

