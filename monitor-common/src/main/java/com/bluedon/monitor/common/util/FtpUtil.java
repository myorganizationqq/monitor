package com.bluedon.monitor.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * @author : jason
 * @date : 2015年2月13日
 * @module : 公共模块
 * @comment : FTP上传功能
 */
public class FtpUtil {

	private static final Logger log = Logger.getLogger(FtpUtil.class);

	private String ip;
	private String userName;
	private String passWord;
	private String port;
	private String encoding;
	private FTPClient ftp;
	public static String ENCODING_GB2312 = "GB2312";
	public static String ENCODING_GBK = "GBK";
	public static String ENCODING_UTF8 = "UTF8";

	/**
	 * 初始化FTP参数
	 *
	 * @param ip
	 *            ftp服务器ip
	 * @param userName
	 *            ftp服务器用户名
	 * @param passWord
	 *            ftp服务器密码
	 * @param port
	 *            ftp服务器端口
	 * @param encoding
	 *            ftp服务器字符集[常量]
	 */
	public FtpUtil(String ip, String userName, String passWord, String port,
			String encoding) {
		this.ip = ip;
		this.userName = userName;
		this.passWord = passWord;
		this.port = port;
		this.encoding = encoding;
	}

	/**
	 * 配置ftp客户端环境
	 *
	 * @return
	 * @throws Exception
	 */
	public FTPClientConfig getFTPClientConfig() throws Exception {
		String systemKey = FTPClientConfig.SYST_UNIX;
		String serverLanguageCode = "zh";
		FTPClientConfig conf = new FTPClientConfig(systemKey);
		conf.setServerLanguageCode(serverLanguageCode);
		conf.setDefaultDateFormatStr("yyyy-MM-dd");
		return conf;
	}

	/**
	 * 关闭ftp连接
	 *
	 * @return
	 */
	public boolean closeFtp() {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
				this.ftp = null;
				return true;
			} catch (Exception e) {
				log.info("ftp closing error!");
				return false;
			}
		}
		log.info("ftp is closed success!");
		return true;
	}

	/**
	 * 初始化ftp客户端
	 *
	 * @return
	 */
	public boolean initAndConnect() {
		ftp = new FTPClient();
		try {
			// 配置ftp客户端环境
			ftp.configure(getFTPClientConfig());
			// 连接ftp服务器并登陆
			ftp.connect(this.ip, Integer.parseInt(this.port));
			if (!ftp.login(this.userName, this.passWord)) {
				this.closeFtp();
				ftp = null;
				return false;
			}
			/**
			 * 登陆成功之后开始进行一系列的ftp设置
			 */
			// 设置ftp超时时间
			ftp.setConnectTimeout(ConfigReader.getInt("ftp", "FTP_TIMEOUT"));
			// 设置ftp客户端缓存大小
			ftp.setBufferSize(ConfigReader.getInt("ftp", "FTP_BUFFER_SIZE"));
			// 设置ftp传输文件类型，默认是ASCII(字符模式),修改为BINARY(二进制模式)
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// 设置ftp字符集模式
			ftp.setControlEncoding(this.encoding);
			/**
			 * 设置ftp交互模式为主动模式:
			 * 被动模式(enterLocalPassiveMode)与主动模式(enterLocalActiveMode)
			 * 区别在于端口维护方的关系。 主动模式由客户端维护端口并可以指定端口，而被动模式则由服务器端来开通指定端口进行交互
			 */
			ftp.enterLocalActiveMode();
			/**
			 * 先使用ftp.getReplyCode()发起对FTP的通信，然后使用FTPReply.isPositiveCompletion(
			 * )判断响应是否成功
			 */
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				return true;
			} else {
				log.info("ftp login failed!");
				this.closeFtp();
				return false;
			}
		} catch (Exception e) {
			log.info("ftp initing error!");
			return false;
		}
	}

	/**
	 * 在ftp服务器新建目录
	 *
	 * @param name
	 *            创建目录名称
	 * @return
	 */
	public boolean mkDir(String name) {
		try {
			if (!this.ftp.makeDirectory(name)) {
				return false;
			}
		} catch (Exception e) {
			log.info("make dir is error!");
			return false;
		}
		return true;
	}

	/**
	 * 删除ftp服务器根路径下目录
	 *
	 * @param dirName
	 *            删除目录的名称
	 * @return
	 */
	public boolean removeDir(String dirName) {
		try {
			return this.ftp.removeDirectory(dirName);
		} catch (Exception e) {
			log.info("remove error!");
		}
		return true;
	}

	/**
	 * 删除ftp服务器上的文件
	 *
	 * @param fileName
	 * @return
	 */
	public boolean removeFile(String fileName) {
		try {
			return this.ftp.deleteFile(fileName);
		} catch (Exception e) {
			log.info("delete error!");
			return false;
		}
	}

	/**
	 * 切换ftp服务器目录
	 *
	 * @param dirPath
	 *            切换路径
	 * @return
	 */
	public boolean changeDir(String dirPath) {
		try {
			if (!this.ftp.changeWorkingDirectory(dirPath)) {
				return false;
			}
		} catch (Exception e) {
			log.info("change work dir is error!");
			return false;
		}
		return true;
	}

	/**
	 * 上传本地文件到ftp服务器根目录
	 *
	 * @param localFile
	 *            本地文件路径(含文件名)
	 * @param newName
	 *            上传ftp后文件名
	 * @return
	 */
	public boolean uploadFile(String localFile, String newName) {
		InputStream input = null;
		boolean success = false;
		try {
			File file = null;
			if (new File(localFile).exists()) {
				file = new File(localFile);
			}
			input = new FileInputStream(file);
			success = ftp.storeFile(newName, input);
			if (!success) {
				log.error("upload failed!");
			}
		} catch (Exception e) {
			log.info("upload failed!");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					log.info("input close() error");
				}
			}
		}
		return success;
	}

	/**
	 * 上传输入流到ftp服务器根目录
	 *
	 * @param input
	 *            上传文件流
	 * @param newName
	 *            新文件名
	 * @throws Exception
	 */
	public boolean uploadFile(InputStream input, String newName)
			throws Exception {
		boolean success = false;
		success = ftp.storeFile(newName, input);
		if (!success) {
			log.info("文件上传失败!");
		}
		if (input != null) {
			input.close();
		}
		return success;
	}

	/**
	 * 上传本地文件到ftp服务器指定目录
	 *
	 * @param localFile
	 *            本地文件路径(含文件名)
	 * @param newName
	 *            新文件名
	 * @param remoteFoldPath
	 *            ftp服务器路径
	 * @throws Exception
	 */
	public boolean uploadFile(String localFile, String newName,
			String remoteFoldPath){
		InputStream input = null;
		boolean success = false;
		File file = null;
		if (new File(localFile).exists()) {
			file = new File(localFile);
		}
		try {
			input = new FileInputStream(file);
			// 改变当前路径到指定路径
			if (!this.changeDir(remoteFoldPath)) {
				log.info("服务器路径不存!");
			}else{
				success = ftp.storeFile(newName, input);
			}
			if (!success) {
				log.info("文件上传失败!");
			}
		} catch (Exception e) {
			log.error("文件上传异常,"+e.getMessage());
		}finally{
			if (input != null) {
				try {
					input.close();
				} catch (Exception e2) {
					log.error("文件流关闭异常,"+e2.getMessage());
				}
			}
		}
		return success;
	}

	/**
	 * 上传输入流到ftp服务器指定目录
	 *
	 * @param input
	 *            ftp上传输入流
	 * @param newName
	 *            新文件名
	 * @param remoteFoldPath
	 *            ftp服务器指定目录名称
	 * @throws Exception
	 */
	public boolean uploadFile(InputStream input, String newName,
			String remoteFoldPath) throws Exception {
		boolean success = false;
		// 改变当前路径到指定路径
		if (!this.changeDir(remoteFoldPath)) {
			log.info("服务器路径不存!");
			return false;
		}
		success = ftp.storeFile(newName, input);
		if (!success) {
			log.info("文件上传失败!");
		}
		if (input != null) {
			input.close();
		}
		return success;
	}

	/**
	 * 更改文件名
	 *
	 * @param oldName
	 *            旧文件名
	 * @param newName
	 *            新文件名
	 * @return
	 * @throws Exception
	 */
	public boolean renameFile(String oldName, String newName) throws Exception {
		return ftp.rename(oldName, newName);
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */

	/*
	 * public static void main(String[] args) { FtpUtil ftp = null; try {
	 * System.out.println("start"); ftp = new FtpUtil("10.17.81.129", "udb",
	 * "udb", "21", FtpUtil.ENCODING_UTF8); if (ftp.initAndConnect()) {
	 * ftp.mkDir("demo"); // 新建 ftp.removeDir("test"); // 删除
	 * ftp.changeDir("demo/"); ftp.uploadFile("D:/smack-1.5.0.jar",
	 * "smack-1.5.0.jar"); InputStream in = new FileInputStream(new File(
	 * "D:/smack-1.5.0.jar")); ftp.uploadFile(in, "abc.rar", "demo/");
	 * ftp.changeDir("demo/"); ftp.renameFile("haha.rar", "new.rar");
	 * ftp.removeFile("abc.rar"); ftp.removeDir("Show1");
	 * ftp.removeFile("FirstFile.txt"); } } catch (Exception e) {
	 * log.info("upload error!"); } finally { if (ftp != null) { ftp.closeFtp();
	 * } } }
	 */

}
