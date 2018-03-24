package com.bluedon.monitor.project.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SendMsgUtil {
	//日志记录对象
    private static final Logger log = Logger.getLogger(SendMsgUtil.class);

	public static void main(String[] args) {
		List<String> phoneUser = new ArrayList<>();
		String content = "冯志辉正在测试短信发送，收到信息给我截图，谢谢！";
		phoneUser.add("15976531601");
		phoneUser.add("13610176397");
		sendMessage(phoneUser, content);
	}
	
	/**
	 * 发送短信
	 * @param phoneUser
	 * @param content
	 */
	public static void sendMessage(List<String> phoneUser, String content) {
		// 发送中文时需要指定encoding为U
		String urlStr = "http://10.90.50.52:8090/send?password=1&text=%s&recipient=%s&encoding=U";
		String phoneNum = "";
		for (String phone : phoneUser) {
			phoneNum += phone + ",";
        }
		URL url;
		try {
			urlStr = String.format(urlStr, URLEncoder.encode(content, "UTF-8"), phoneNum);
			log.info("发送地址：" + urlStr);
			url = new URL(urlStr);
			URLConnection URLconnection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				log.info("发送成功！");
				InputStream urlStream = httpConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));
				String sCurrentLine = "";
				String sTotalString = "";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				log.info(URLDecoder.decode(sTotalString, "UTF-8"));
				// 假设该url页面输出为"OK"
				if (sTotalString.equals("OK")) {

				} else {
					
				}
			} else {
				log.info("发送失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
