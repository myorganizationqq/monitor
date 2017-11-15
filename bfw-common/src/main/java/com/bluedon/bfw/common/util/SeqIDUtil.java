package com.bluedon.bfw.common.util;

import java.util.UUID;

/**
 * @author : jason
 * @date : 2015年3月16日
 * @module : 公共模块
 * @comment : UUID生成类
 */
public final class SeqIDUtil {

	private static SeqIDUtil instance = new SeqIDUtil();

	private SeqIDUtil() {
	}

	public static SeqIDUtil getInstance() {
		return instance;
	}

	public String getSeqID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
