package com.zhouhuan.common.custom;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhouhuan.common.utils.LogUtil;

public class SystemAspect {
	
	@Around("within(@org.springframework.stereotype.Controller *)")
	public Object process(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object retVal = pjp.proceed();
		Object rs = retVal;
		request.setAttribute(LogUtil.SYS_LOG_RTNDATA, rs);
		return rs;
	}
}
