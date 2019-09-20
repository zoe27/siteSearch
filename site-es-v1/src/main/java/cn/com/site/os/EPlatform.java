/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年9月19日 下午10:31:29
 * EPlatform.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.os;

public enum EPlatform {
	Any("any"), Linux("Linux"), Mac_OS("Mac OS"), Mac_OS_X("Mac OS X"), Windows("Windows"), OS2("OS/2"), Solaris(
			"Solaris"), SunOS("SunOS"), MPEiX("MPE/iX"), HP_UX("HP-UX"), AIX("AIX"), OS390("OS/390"), FreeBSD(
					"FreeBSD"), Irix("Irix"), Digital_Unix(
							"Digital Unix"), NetWare_411("NetWare"), OSF1("OSF1"), OpenVMS("OpenVMS"), Others("Others");

	private EPlatform(String desc) {
		this.description = desc;
	}

	public String toString() {
		return description;
	}

	private String description;
}
