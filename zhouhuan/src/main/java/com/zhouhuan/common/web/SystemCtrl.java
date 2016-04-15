package com.zhouhuan.common.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemCtrl {

	@RequestMapping(value = "/test")
	@ResponseBody
	public Object test(){
		Map m = new HashMap();
		m.put("code", 1);
		m.put("msg", "success");
		return m;
	}
	
}
