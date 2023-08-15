package com.yf.exam.core.utils.file;

import java.security.MessageDigest;


/**
 * MD5工具类
 * ClassName: MD5Util <br/>
 * date: 2018年1月13日 下午6:54:53 <br/>
 *
 * @author Bool
 * @version
 */
public class Md5Util {


	/**
	 * 简单MD5
	 * @param str
	 * @return
	 */
	public static String md5(String str) {

		try {
   		 	MessageDigest md = MessageDigest.getInstance("MD5");
   	        byte[] array = md.digest(str.getBytes("UTF-8"));
   	        StringBuilder sb = new StringBuilder();
   	        for (byte item : array) {
   	            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
   	        }
   	        return sb.toString();
	   	}catch(Exception e) {
	   		 return null;
	   	}
	}

}
