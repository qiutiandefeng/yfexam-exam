package com.yf.exam.core.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * MD5工具类
 * ClassName: MD5Util <br/>
 * date: 2018年1月13日 下午6:54:53 <br/>
 *
 * @author Bool
 * @version
 */
public class MD5Util {

	
	/**
	 * 简单MD5
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {

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

	/**
	 * 获得文件的MD5值
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte [] buffer = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

}
