package com.zhouhuan.common.utils;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

public class LogUtil {

	public static final Logger ERROR_LOG = LoggerFactory.getLogger("zhserver-error");
	public static final Logger ACCESS_LOG = LoggerFactory.getLogger("zhserver-access");
	public static final String tag = "";
	public static final String SYS_COST="sysBegin";
	public static final String SYS_LOG_UUID="log_uuid";
	public static final String SYS_LOG_SIGN="log_sign";
	public static final String SYS_LOG_PARAMS="log_params";
	public static final String SYS_LOG_RTNDATA="log_rtnData";
	public static final String SYS_LOG_METAINFO="log_metaInfo";


	/**
	 * 记录访问日志
	 * [cost][uuid][jsessionid][ip][accept][UserAgent][url][params][headers][Referer]
	 * 
	 * @param request
	 */
	public static void logAccess(HttpServletRequest request) {
		getAccessLog().info(accessString(request));
	}
	
	/**
	 * 记录异常错误 格式 [exception]
	 * 
	 * @param message
	 * @param e
	 */
	public static void logError(String message, Throwable e) {
		StringBuilder s = new StringBuilder();
		s.append(getBlock("exception"));
		s.append(getBlock(message));
		getErrorLog().error(s.toString(), e);
	}
	
	
	/**
	 * 拼接请求日志
	 * 
	 * @param request
	 * @return
	 */
	public static String accessString(HttpServletRequest request) {
		String ip = RequestUtil.getRequestIp(request);
		String params = (String) request.getAttribute(SYS_LOG_PARAMS);
        String headers = getHeaders(request);
        String sign = (String) request.getAttribute(SYS_LOG_SIGN);
		String rtnData = JSONObject.toJSONString(request.getAttribute(SYS_LOG_RTNDATA));
		return LogFormat.accessformat("(" + ip + ")(" + params + ")(" + headers + ")(" + sign +")", rtnData);
    }


	public static String getBlock(Object msg) {
		if (msg == null) {
			msg = "";
		}
		return tag+"[" + msg.toString() + "]";
	}

	protected static String getParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		return JSON.toJSONString(params);
	}

	private static String getHeaders(HttpServletRequest request) {
		Map<String, String> headers = Maps.newHashMap();
		Enumeration<String> namesEnumeration = request.getHeaderNames();
		while (namesEnumeration.hasMoreElements()) {
			String name = namesEnumeration.nextElement();
			Enumeration<String> valueEnumeration = request.getHeaders(name);
			StringBuffer values = new StringBuffer();
			while (valueEnumeration.hasMoreElements()) {
				values.append(",").append(valueEnumeration.nextElement());
			}
			headers.put(name, values.deleteCharAt(0).toString());
		}
		return JSON.toJSONString(headers);
	}

	private static Logger getAccessLog() {
		return ACCESS_LOG;
	}

	private static Logger getErrorLog() {
		return ERROR_LOG;
	}
	
	
}
