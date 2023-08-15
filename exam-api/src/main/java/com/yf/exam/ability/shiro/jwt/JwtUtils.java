package com.yf.exam.ability.shiro.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yf.exam.core.utils.file.Md5Util;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 * @author bool
 */
public class JwtUtils {

	/**
	 * 有效期24小时
	 */
	private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;


	/**
	 * 校验是否正确
	 * @param token
	 * @param username
	 * @return
	 */
	public static boolean verify(String token, String username) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(encryptSecret(username));
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("username", username)
					.build();
			// 效验TOKEN
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}





	/**
	 * 从Token中解密获得用户名
	 * @param token
	 * @return
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成JWT Token字符串
	 * @param username
	 * @return
	 */
	public static String sign(String username) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(encryptSecret(username));
		// 附带username信息
		return JWT.create()
				.withClaim("username", username)
				.withExpiresAt(date).sign(algorithm);

	}

	/**
	 * 根据用户名和秘钥，生成一个新的秘钥，用于JWT加强一些安全性
	 * @param userName
	 * @return
	 */
	private static String encryptSecret(String userName){

		// 一个简单的登录规则，用户名+当前月份为加密串，意思每个月会变，要重新登录
		// 可自行修改此规则
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer(userName)
				.append("&")
				.append(cl.get(Calendar.MONTH));

		// 获取MD5
		String secret = Md5Util.md5(sb.toString());

		return  Md5Util.md5(userName + "&" + secret);
	}
}
