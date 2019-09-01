/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年9月1日 下午2:02:36
 * EsConfInfo.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.es.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="elastic")
@PropertySource("classpath:esConnectInfo.properties")
public class EsConfInfo {
	
	private String ip;
	
	private int port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}

