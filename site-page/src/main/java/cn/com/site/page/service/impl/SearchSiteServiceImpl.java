package cn.com.site.page.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.site.es.esUtil.ESClient;
import cn.com.site.es.util.ESConstant;
import cn.com.site.page.dto.SiteResDto;
import cn.com.site.page.service.SearchService;

/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年8月4日 上午10:33:32
 * SearchSiteServiceImpl.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
@Service
public class SearchSiteServiceImpl implements SearchService {
	
	@Autowired
	private ESClient esclient;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see cn.com.site.page.service.SearchService#searchByQuery(java.lang.String, int, int)
	 */
	@Override
	public List<SiteResDto> searchByQuery(String query, int begin, int limit) {
		List<SiteResDto> list = Lists.newArrayList();
		// TODO Auto-generated method stub
		SearchResponse response = esclient.getData(ESConstant.ES_SITE_INFO.INDEX.getInfo(),ESConstant.ES_SITE_INFO.TYPE.getInfo(),query);
		SearchHits hits = null;
		if(response != null) {
			hits = response.getHits();
		}
		if(hits != null) {
			hits.forEach(hit->{
				String title = hit.getSourceAsMap().get(ESConstant.TITLE).toString();
				String url = hit.getSourceAsMap().get(ESConstant.URL).toString();
				if (StringUtils.endsWith(url, "/")) {
					url = StringUtils.substringBeforeLast(url, "/");
				}
				String keywords = hit.getSourceAsMap().get(ESConstant.KEY_WORDS).toString();
				String imagePath = hit.getSourceAsMap().get(ESConstant.IMAGE_PATH).toString();

				SiteResDto siteDto = new SiteResDto(title, url, imagePath, keywords);
				list.add(siteDto);
			});
		}
		return list;
	}

}

