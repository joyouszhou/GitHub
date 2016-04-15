package com.zhouhuan.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {
private static Logger log = LoggerFactory.getLogger(RequestUtil.class);
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String getRequestIp(HttpServletRequest request){
		String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            // 防止多级ip代理，生成多个ip，第一个为真实ip
            if (!StringUtils.isEmpty(ip) && ip.length() > 15 && ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        } catch (Exception e) {
            log.error("从request中获取ip失败，失败原因：" + e);
        }
        return ip;
	}
	
	/**
	 * 获取被请求url
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		StringBuffer url = new StringBuffer();
		String scheme = request.getScheme();
		int port = request.getServerPort();
		if (port < 0) port = 80;
		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(request.getRequestURI());
		String queryString = request.getQueryString();
		if (queryString != null) url.append('?').append(queryString);
		return url.toString();
	}
}
