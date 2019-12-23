/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2019年10月8日 下午10:41:34
 * PcOrMobile.java
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @since JDK 1.8
 */
package cn.com.site.page.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断移动端还是PC端
 */
public class PcOrMobile {

    private static Logger log = LoggerFactory.getLogger(PcOrMobile.class);

    // 手机端正则表达式
    private static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i" + "|windows (phone|ce)|blackberry"
            + "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp" + "|laystation portable)|nokia|fennec|htc[-_]"
            + "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    // pad端正则表达式
    private static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    // 移动设备正则匹配：手机端、平板
    private static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    private static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

    /**
     * 通过UA判断是否是PC端
     *
     * @param userAgent
     * @return
     */
    public static boolean isMobile(String userAgent) {
        if (null == userAgent) {
            userAgent = "";
        }
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if (matcherPhone.find() || matcherTable.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是移动端
     *
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {
        boolean isFromMobile = false;

        HttpSession session = request.getSession();
        // 检查是否已经记录访问方式（移动端或pc端）
        if (null == session.getAttribute("ua")) {
            try {
                // 获取ua，用来判断是否为移动端访问
                String userAgent = request.getHeader("USER-AGENT").toLowerCase();
                if (null == userAgent) {
                    userAgent = "";
                }
                isFromMobile = isMobile(userAgent);
                // 判断是否为移动端访问
				/*if (isFromMobile) {
					session.setAttribute("ua", "mobile");
				} else {
					session.setAttribute("ua", "pc");
				}*/
            } catch (Exception e) {
                log.error("", e);
            }
        } else {
            isFromMobile = session.getAttribute("ua").equals("mobile");
        }
        log.info("The access if from mobile {}", isFromMobile);
        return isFromMobile;
    }

}
