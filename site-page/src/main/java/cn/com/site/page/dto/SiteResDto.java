package cn.com.site.page.dto;

/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年8月4日 上午10:25:33
 * SiteResDto.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
public class SiteResDto {
	
	// 网站标题
	private String title;

	// 网站url地址
	private String url;
	
	// 网站图片地址，暂时为空
	private String imageUrl;
	
	// 网站描述,暂时为空
	private String siteDesc;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSiteDesc() {
		return siteDesc;
	}

	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}

	/**
	 * @param title
	 * @param url
	 * @param imageUrl
	 * @param siteDesc
	 */
	public SiteResDto(String title, String url, String imageUrl, String siteDesc) {
		super();
		this.title = title;
		this.url = url;
		this.imageUrl = imageUrl;
		this.siteDesc = siteDesc;
	}

}

