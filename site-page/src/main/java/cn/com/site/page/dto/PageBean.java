/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2019年12月2日 下午10:50:08
 * PageBean.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.dto;

/**
 * 页面对象
 * @param <T>
 */
public class PageBean<T> {
	
	private int size = 10;
	
	private int begin;
	
	private T t;

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	

}

