package com.zhouhuan.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class SignUtil {
	protected final static Logger logger = LoggerFactory.getLogger(SignUtil.class);
	/**
	 * ip白名单
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isIpList(String ip) {
		String iplist = PropertiesUtil.getProperty("iplist");
		if (iplist != null && !StringUtils.isEmpty(iplist)) {
			String writeList[] = PropertiesUtil.getProperty("iplist").split(";");
			for (String po : writeList) {
				if (po != null && ip.equals(po)) {
					return true;
				}
			}
		}
		return false;
	}
}
