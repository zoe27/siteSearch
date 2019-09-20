/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年9月20日 下午8:56:15
 * PathUtil.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.util;

import java.io.File;
import java.io.IOException;

public class PathUtil {

	/**
	 * 获取项目所在的路径
	 * @return
	 */
	public static String resourcePath() {
		String path = System.getProperty("user.dir");
		int lastIdx = path.lastIndexOf("/") + 1;
		path = path.substring(0, lastIdx);
		return path;
	}
	
	public static void main(String[] args) throws IOException {
		//当前项目下路径
        File file = new File("");
        String filePath = file.getCanonicalPath();
        file.getParent();
        System.out.println(resourcePath());
 
        //当前项目下xml文件夹
        File file1 = new File("");
        String filePath1 = file1.getCanonicalPath()+File.separator+"xml\\";
        System.out.println(filePath1);
 
        //获取类加载的根路径
        File file3 = new File(PathUtil.class.getResource("/").getPath());
        System.out.println(file3);
 
        //获取当前类的所在工程路径
        File file4 = new File(PathUtil.class.getResource("").getPath());
        System.out.println(file4);
 
        //获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
	}
}
