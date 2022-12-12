package com.yf.exam.ability.shiro.jwt;
 
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author bool
 */
@Data
public class JwtToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 1L;

    /**
     * JWT的字符token
     */
	private String token;


    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}
