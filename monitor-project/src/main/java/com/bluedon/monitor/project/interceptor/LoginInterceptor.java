package com.bluedon.monitor.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.project.controller.netio.NetioController;
import com.bluedon.monitor.project.util.JedisUtil;
import com.bluedon.monitor.project.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;


public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(LoginInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest httpRequest,
                                HttpServletResponse httpResponse, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {


    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {

        Cookie[] cookies = request.getCookies();

        String cookieSid = null;

        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("sid")) {
                cookieSid = cookies[i].getValue();
            }
        }

        if (StringUtils.isEmpty(cookieSid)) {
            response.sendRedirect("http://10.90.2.117/sys/login");
            return false;
        }

        return true;


    }

}