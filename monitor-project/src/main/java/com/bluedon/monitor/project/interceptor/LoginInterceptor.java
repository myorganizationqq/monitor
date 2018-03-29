package com.bluedon.monitor.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


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

    /**
     * 通过跟路径是否有cookie account 判断richapm是否登录，没有则拦截一体化监控所有请求并跳转至richapm登录页面
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {

        Cookie[] cookies = request.getCookies();

        if(cookies==null) return true;

        String cookieAccount = null;

        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("account")) {
                cookieAccount = cookies[i].getValue();
            }
        }

        if (StringUtils.isEmpty(cookieAccount)) {
            java.io.PrintWriter out = response.getWriter();

            out.println("<html><script>window.open('http://10.90.2.117/sys/login','_top')</script></html>");

            return false;
        }

        return true;


    }

}