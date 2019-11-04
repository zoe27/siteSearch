package cn.com.site.page.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
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
		long time = System.currentTimeMillis();
		if(response != null) {
			hits = response.getHits();
		}
		if(hits != null) {
			hits.forEach(hit->{
				String title = hit.getSourceAsMap().get(ESConstant.TITLE).toString();
				String url = hit.getSourceAsMap().get(ESConstant.URL).toString();
				// 这里是为了防止前端按钮出不来的问题
				if (StringUtils.endsWith(url, "/")) {
					url = StringUtils.substringBeforeLast(url, "/");
				}
				String keywords = hit.getSourceAsMap().get(ESConstant.KEYWORDS).toString();
				String imagePath = hit.getSourceAsMap().get(ESConstant.IMAGE_PATH).toString();
				String desc = hit.getSourceAsMap().get(ESConstant.DESC).toString();

				if(StringUtils.isNotBlank(desc)) {
					keywords = desc;
				}
				SiteResDto siteDto = new SiteResDto(title, url, imagePath, keywords);
				list.add(siteDto);
			});
		}
		log.info("get data from es cost time is {}, query is {}", (System.currentTimeMillis() - time), query);
		return list;
	}

	/* (non-Javadoc)
	 * @see cn.com.site.page.service.SearchService#searchRecent()
	 */
	@Override
	public List<SiteResDto> searchRecent() {
		List<SiteResDto> list = Lists.newArrayList();
		// TODO Auto-generated method stub
		SearchResponse response = esclient.getRecentData(ESConstant.ES_SITE_INFO.INDEX.getInfo(),ESConstant.ES_SITE_INFO.TYPE.getInfo());
		SearchHits hits = null;
		long time = System.currentTimeMillis();
		if(response != null) {
			hits = response.getHits();
		}
		if(hits != null) {
			for(int i = 0; i < hits.getHits().length; i++){
				SearchHit hit = hits.getHits()[i];
				String title = hit.getSourceAsMap().get(ESConstant.TITLE).toString();
				// title 长度控制在15个字符左右
				if(StringUtils.isNotBlank(title) && title.length() > 15) {
					title = title.substring(0,15).concat("....");
				}
				String url = hit.getSourceAsMap().get(ESConstant.URL).toString();
				// 这里是为了防止前端按钮出不来的问题
				if (StringUtils.endsWith(url, "/")) {
					url = StringUtils.substringBeforeLast(url, "/");
				}
				String keywords = hit.getSourceAsMap().get(ESConstant.KEYWORDS).toString();
				String imagePath = hit.getSourceAsMap().get(ESConstant.IMAGE_PATH).toString();
				String desc = hit.getSourceAsMap().get(ESConstant.DESC).toString();

				if(StringUtils.isNotBlank(desc)) {
					keywords = desc;
				}
				SiteResDto siteDto = new SiteResDto(title, url, imagePath, keywords);
				list.add(siteDto);
			};
		}
		log.info("get recent data from es cost time is {}, query is {}", (System.currentTimeMillis() - time));
		list = list.size() > 10 ? list.subList(0, 10) : list;
		return list;
	}

}

