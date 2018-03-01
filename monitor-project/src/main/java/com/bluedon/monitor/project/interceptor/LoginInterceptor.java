package com.bluedon.monitor.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.project.controller.netio.NetioController;
import com.bluedon.monitor.project.util.JedisUtil;
import com.bluedon.monitor.project.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;
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

        String cookieAccount = null;
        String cookieSid = null;

        for (int i = 0; i < cookies.length; i++) {

            if (cookies[i].getName().equals("account")) {
                cookieAccount = cookies[i].getValue();
            }

            if (cookies[i].getName().equals("sid")) {
                cookieSid = cookies[i].getValue();
            }
        }


        log.info("******cookieAccount" + cookieAccount);
        log.info("******cookiesid" + cookieAccount);

        /*java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('http://www.richapm.com/sys/login','_top')");
        out.println("</script>");
        out.println("</html>");

        if (cookieSid == null || cookieAccount == null) return false;

        //请求redis 判断是否已登录
        String host = PropertiesUtil.getValue("richapm.redis.host");
        int port = Integer.parseInt(PropertiesUtil.getValue("richapm.redis.port"));
        Jedis jedis = new Jedis(host, port);
        jedis.select(2);

        byte[] sidSerialize = SerializationUtils.serialize(cookieSid);
        byte[] bytes = jedis.get(sidSerialize);

        log.info("******bytes" + bytes);

        if (bytes == null)
            return false;
*/
        return true;


    }

}