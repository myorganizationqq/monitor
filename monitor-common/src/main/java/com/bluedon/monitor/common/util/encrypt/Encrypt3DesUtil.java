package com.bluedon.monitor.common.util.encrypt;

import org.apache.log4j.Logger;

/**
 * @author  : jason
 * @date    : 2015年3月9日
 * @module  : 公共模块
 * @comment : 系统3DES加密
 */
public class Encrypt3DesUtil {

	private static DesEncrypt encrypt;

	// private static String keyStore = "123456" ; ;
	// private static String keyStore =
	// PropertyReader.getProperty("INTF.KeyStore") ;

	// 备注：密钥必须为24位 3*8
	// private final static String
	// keyStore="CFB3A7BC9F987F649C853EE84122F99644F824766E881A20";
	private final static String keyStore = "F87F649C853EE84122F99644";
	// 获取Log4j对象
	private static Logger logger = Logger.getLogger(Encrypt3DesUtil.class);

	private Encrypt3DesUtil() {

	}

	private static DesEncrypt getEncrypt() {
		init();
		return encrypt;
	}

	// 初始化
	private static void init() {
		if (encrypt == null) {
			// String keyStore =
			if (keyStore == null || "".equals(keyStore.trim())) {
				logger.error("初始化失败..数据库中未配置 INTF.KeyStore字段.");
			}
			encrypt = new DesEncrypt(keyStore);
			logger.info("初始化3DesUtil成功...");
		}
	}

	/**
	 *  加密字符串,返回String的密文
	 * @param inputText
	 *  要加密的字符串
	 * @return
	 * 	加密后的字符串
	 */
	public static String getEncString(String inputText) {

		return getEncrypt().getEncString(inputText);
	}

	/**
	 * 	把String 类型的密文解密
	 * @param inputText
	 * 	要解密的密文字符串
	 * @return
	 * 	解密后的明文字符串
	 */
	public static String getDesString(String inputText) {

		return getEncrypt().getDesString(inputText);
	}

	public static void main(String[] args) {
		String inputText = "123456";
		System.out.println("原文:" + inputText);
		String output = Encrypt3DesUtil.getEncString(inputText);
		System.out.println("密文:" + output);
		System.out.println("---------------------");
		System.out.println("---------------------");
		System.out.println("密文:" + output);
		String output2 = Encrypt3DesUtil.getDesString(output);
		System.out.println("明文:" + output2);
		System.out.println(output.length());
		System.out.println(output2.length());
	}

}
