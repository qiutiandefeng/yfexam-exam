package com.yf.exam.core.utils.passwd;

/**
 * 密码实体
 * ClassName: PassInfo <br/>
 * date: 2018年2月13日 下午7:13:50 <br/>
 *
 * @author Bool
 * @version
 */
public class PassInfo {
	
	//密码随机串码
	private String salt;
	
	//MD5后的密码
	private String password;

	public PassInfo(String salt, String password) {
		super();
		this.salt = salt;
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

