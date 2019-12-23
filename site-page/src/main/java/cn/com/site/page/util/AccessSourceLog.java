/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2019年12月23日 下午10:35:13
 * AccessSourceLog.java
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @since JDK 1.8
 */
package cn.com.site.page.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问来源日志
 */
public class AccessSourceLog {

    private static Logger log = LoggerFactory.getLogger(AccessSourceLog.class);

    /**
     * 访问来源日志
     *
     * @param request
     */
    public static void accessLog(HttpServletRequest request) {
        log.info("[Read From IP]: {}， uri is {}，is mobile {} ", IpUtils.getIpAdrress(request), request.getRequestURI(), PcOrMobile.isMobile(request));

    }

}

