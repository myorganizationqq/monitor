package com.bluedon.monitor.common.util.encrypt;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author  : jason
 * @date    : 2015年3月9日
 * @module  : 公共模块 
 * @comment : 加密辅助类
 */
public class DesEncrypt {
	

	/**
	 * 
	 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储.
	 */
	private SecretKey key = null;
	private static String Algorithm = "DESede";
	private static final Logger log = Logger.getLogger(DesEncrypt.class);

	// 24个字符 3*8
	// final static byte[]keybyte={0x11,0x22,0x4F,0x58,
	// (byte)0x88,0x10,0x40,0x38,
	// 0x28,0x25,0x79,0x51, (byte)0xCB,(byte)0xDD,0x55,0x66,
	// 0x77,0x29,0x74, (byte)0x98,0x30,0x40,0x36,(byte)0xE2
	// };
	// final static byte[]keybyte = "wertyuioplkjwertyuioplkj".getBytes() ;
	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	public DesEncrypt() {

	}

	public DesEncrypt(String keyStore) {
		this.getKey(keyStore);
	}

	public void getKey(String strKey) {
		if (check(strKey)) {
			try {
				Security.addProvider(new com.sun.crypto.provider.SunJCE());
				this.key = new SecretKeySpec(strKey.getBytes(), Algorithm);
			} catch (Exception e) {
				log.error("获取Key值无效");
			}
		} else {
			throw new RuntimeException("初始化3DES密钥失败，请检查3DES密钥.");
		}

	}

	public boolean check(String inputText) {
		boolean flag = false;
		if (inputText != null && !"".equals(inputText.trim())) {
			if (inputText.trim().length() == 24) {
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF-8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			log.error("转码错误");
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e) {
			log.error("解密转码错误");
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			log.error("byte输出错误");
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			log.error("解密byte密文输入错误");
		} finally {
			cipher = null;
		}
		return byteFina;

	}

}
