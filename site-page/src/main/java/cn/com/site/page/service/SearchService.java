package cn.com.site.page.service;

import java.util.List;
import cn.com.site.page.dto.SiteResDto;

/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年8月4日 上午10:22:23
 * SearchService.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
public interface SearchService {

	/**
	 * 通过query，按照分页获取内容
	 * @param query
	 * @param begin
	 * @param limit
	 * @return
	 */
	List<SiteResDto> searchByQuery(String query, int begin, int limit);
	
	/**
	 * 获取最新数据
	 * @return
	 */
	List<SiteResDto> searchRecent();

	/**
	 * 随机返回部分数据
	 * @return
	 */
	List<SiteResDto> searchRandom();
}

