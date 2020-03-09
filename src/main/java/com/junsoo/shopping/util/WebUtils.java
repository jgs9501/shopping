package com.junsoo.shopping.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junsoo.shopping.common.service.reigst.UserServiceImpl;

public class WebUtils {

	public String getClientIp(HttpServletRequest request) {
		
		String ip = request.getHeader("X-Forwarded-For");
		
 
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // À¥·ÎÁ÷
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
 
        return ip;
	}
}
