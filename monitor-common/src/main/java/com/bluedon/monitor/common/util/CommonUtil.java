package com.bluedon.monitor.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

/**
 * @author : jason
 * @date : 2015年1月16日
 * @module : 基础模块
 * @comment : 通用辅助类
 */
public class CommonUtil {

    private static final Logger log = Logger.getLogger(CommonUtil.class);

    private static Map <String, Map <String, Method>> cacheMethodMap = new HashMap <String, Map <String, Method>>();
    private static Map <String, Field[]> cacheFieldMap = new HashMap <String, Field[]>();

    /**
     * 将对象按照属性名-属性值的关系输出成字符串供显示
     *
     * @param obj
     * @return
     */
    public static String Object2String(Object obj) {
        if (obj == null) {
            return null;
        }
        String key = obj.getClass().getName();
        StringBuffer strBuf = new StringBuffer();

        if (!cacheMethodMap.containsKey(key)) {
            // long t1 = System.nanoTime();
            Field[] fields = obj.getClass().getDeclaredFields();
            Method[] methods = obj.getClass().getMethods();
            // long t2 = System.nanoTime();
            Map <String, Method> getMethodMap = new HashMap <String, Method>();
            for (Field field : fields) {
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase(
                            "get" + field.getName())) {
                        getMethodMap.put(field.getName(), method);
                        break;
                    }
                }
            }
            cacheMethodMap.put(key, getMethodMap);
            cacheFieldMap.put(key, fields);
        }

        try {
            Object temp = null;
            for (Field field : cacheFieldMap.get(key)) {
                strBuf.append(field.getName());
                strBuf.append("=");
                Method method = cacheMethodMap.get(key).get(field.getName());
                if (method != null) {
                    temp = method.invoke(obj, (Object[]) null);
                }
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-DD HH:mm:ss");
                String time = "";
                if (temp instanceof Calendar) {
                    Calendar cal = (Calendar) temp;
                    try {
                        time = sdf.format(cal.getTime());
                        strBuf.append(time);
                        strBuf.append(",");
                        continue;
                    } catch (Exception e) {
                        log.error("******Object2String: Calendar Format Error!");
                    }
                }
                if (temp instanceof Date) {
                    Date date = (Date) temp;
                    try {
                        time = sdf.format(date);
                        strBuf.append(time);
                        strBuf.append(",");
                        continue;
                    } catch (Exception e) {
                        log.error("******Object2String: Date Format Error!");
                    }
                } else {
                    strBuf.append(temp);
                    strBuf.append(",");
                }
            }
        } catch (Exception e) {
            log.error("****** Error!");
        }
        if (strBuf != null && !("".equals(strBuf.toString()))
                && strBuf.toString().endsWith(",")) {
            strBuf.deleteCharAt(strBuf.lastIndexOf(","));
        }
        return strBuf.toString();
    }

    /**
     * 对前台进行encodeURI()方法编码的值进行解码
     *
     * @param orgnValue 编码后的值
     * @param charType  解码字符类型[默认UTF-8]
     * @return 返回解码后的内容
     */
    public static String decode(String orgnValue, String charType) {
        try {
            if (orgnValue == null) {
                return null;
            }
            return URLDecoder.decode(orgnValue, charType == null ? "UTF-8"
                    : charType);
        } catch (Exception e) {
            log.error("******CommonUtil: decode error! param:" + orgnValue
                    + "\tcharType:" + charType);
        }
        return null;
    }

    /**
     * 对原对象进行深度克隆
     *
     * @param srcObj 需要克隆的源对象[PS:此对象必须要求实现Serializable接口]
     * @return
     */
    public final static Object objectCopy(Object srcObj) {
        Object dstObject = null;
        try {
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ObjectOutputStream objOutput = new ObjectOutputStream(byteOutput);
            objOutput.writeObject(srcObj);// 克隆源对象
            ByteArrayInputStream byteInput = new ByteArrayInputStream(
                    byteOutput.toByteArray());
            ObjectInputStream objInput = new ObjectInputStream(byteInput);
            dstObject = objInput.readObject();// 克隆目标对象
        } catch (IOException e) {
            log.error("******ObjectCopy Error!");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            log.error("******ObjectCopy Error!");
            //e.printStackTrace();
        }
        return dstObject;
    }

    /**
     * 根据需要长度和需要的位置将需要的字符串填充到随机数字中
     *
     * @param length      随机结果的长度[必传,不能大于10]
     * @param location    填充占位符的位置：0:左边,1:右边[此值为空则右对齐,左补占位符]
     * @param placeHolder 填充占位符[为空则以0填充]
     * @return
     */
    public static String getRandomStr(Integer length, Integer location,
                                      String placeHolder) {
        StringBuffer result = new StringBuffer("");
        if (length == null || length > 10) {
            return null;
        } else {
            // 设置随机基数
            int base = 1;
            for (int i = 0; i < length; i++) {
                base = base * 10;
            }
            Random ran = new Random();
            int number = ran.nextInt(base);
            result.append(number);
            int tmpLength = result.length();
            // 设置占位符
            String place = placeHolder == null ? "0" : placeHolder;
            // 循环填充
            for (int i = 0; i < length - tmpLength; i++) {
                if (Integer.valueOf(1).equals(location)) {
                    result.append(place);
                } else {
                    result.insert(0, place);
                }
            }
        }
        return result.toString();
    }


    /**
     * 获取当天0点和前一天0点时间
     *
     * @return
     */
    public static Map <String, String> getCurrentAndPreTime() {
        String d = DateUtil.dateToString(new Date(), DateUtil.DATE_STYLE_DATE_1);
        String[] arr = d.split("-");
        DateTime dateTime1 = new DateTime(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), 0, 0, 0);
        DateTime dateTime2 = dateTime1.minusHours(24);
        //String T1 = DateUtil.dateToString(dateTime1.toDate(), DateUtil.DATE_STYLE_TIME_1);
        // String T2 = DateUtil.dateToString(dateTime2.toDate(), DateUtil.DATE_STYLE_TIME_1);

        String T2 = "2017-10-1 00:00:00";
        String T1 = "2017-12-1 00:00:00";
        Map <String, String> map = new HashMap <>();
        map.put("currentDay", T1);
        map.put("preDay", T2);
        return map;
    }

    /**
     * 发送邮件和短信息
     * @param head  邮件头
     * @param content 内容
     * @param phones 手机号码
     * @param emails 邮箱
     */
    public static void sendAlarm(String head, String content, List<String> phones, List<String> emails) {

        for (String phone : phones) {
            System.out.println("发送短信给" + phone);
        }

        for (String email : emails) {
            System.out.println("发送邮件给" + email);
            SendMailUtil.getInstance().doSendHtmlEmail(head, content, email);
        }
    }


}
