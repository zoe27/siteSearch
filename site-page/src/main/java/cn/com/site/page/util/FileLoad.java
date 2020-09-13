/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年8月5日 下午11:08:25
 * FileLoad.java
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @since JDK 1.8
 */
package cn.com.site.page.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileLoad {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public List<String> loadKeywords(String filePath) {
		List<String> list = new ArrayList<String>();
		try {
			InputStream is3 = this.getClass().getClassLoader().getResourceAsStream(filePath);
			InputStreamReader streamReader = new InputStreamReader(is3);
			BufferedReader in = new BufferedReader(streamReader);
			// BufferedReader in = new BufferedReader(new FileReader("keywords.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				list.add(str);
				log.info("{}", str);
			}
			
			is3.close();
			streamReader.close();
			in.close();
		} catch (IOException e) {
			log.error("", e);
		}
		return list;
	}

	/**
	 * 加载文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public String loadContent(String filePath) {
		String content = "";
		try {
			InputStream is3 = this.getClass().getClassLoader().getResourceAsStream(filePath);
			InputStreamReader streamReader = new InputStreamReader(is3);
			BufferedReader in = new BufferedReader(streamReader);
			// BufferedReader in = new BufferedReader(new FileReader("keywords.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				content = str;
			}
			
			is3.close();
			streamReader.close();
			in.close();
		} catch (IOException e) {
			log.error("", e);
		} 
		return content;
	}
	
	/**
	 * 往文件中写数据
	 * @param content
	 * @param filePath
	 */
	public void writeData(String content, String filePath) {
	}

	public static void main(String[] args) {
		new FileLoad().loadKeywords(null);
	}
}
