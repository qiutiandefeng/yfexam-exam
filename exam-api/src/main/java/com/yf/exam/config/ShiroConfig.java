package com.yf.exam.config;

import com.yf.exam.ability.shiro.CNFilterFactoryBean;
import com.yf.exam.ability.shiro.ShiroRealm;
import com.yf.exam.ability.shiro.aop.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Shiro配置类
 * @author bool
 */
@Slf4j
@Configuration
public class ShiroConfig {

	/**
	 * Filter Chain定义说明
	 *
	 * 1、一个URL可以配置多个Filter，使用逗号分隔
	 * 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new CNFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器
		Map<String, String> map = new LinkedHashMap<>();

		// 需要排除的一些接口
		map.put("/exam/api/sys/user/login", "anon");
		map.put("/exam/api/sys/user/reg", "anon");
		map.put("/exam/api/sys/user/quick-reg", "anon");

		// 获取网站基本信息
		map.put("/exam/api/sys/config/detail", "anon");

		// 文件读取
		map.put("/upload/file/**", "anon");

		map.put("/", "anon");
		map.put("/v2/**", "anon");
		map.put("/doc.html", "anon");
		map.put("/**/*.js", "anon");
		map.put("/**/*.css", "anon");
		map.put("/**/*.html", "anon");
		map.put("/**/*.svg", "anon");
		map.put("/**/*.pdf", "anon");
		map.put("/**/*.jpg", "anon");
		map.put("/**/*.png", "anon");
		map.put("/**/*.ico", "anon");

		// 字体
		map.put("/**/*.ttf", "anon");
		map.put("/**/*.woff", "anon");
		map.put("/**/*.woff2", "anon");
		map.put("/druid/**", "anon");
		map.put("/swagger-ui.html", "anon");
		map.put("/swagger**/**", "anon");
		map.put("/webjars/**", "anon");

		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
		filterMap.put("jwt", new JwtFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		map.put("/**", "jwt");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean("securityManager")
	public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
		return securityManager;
	}

	/**
	 * 下面的代码是添加注解支持
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		defaultAdvisorAutoProxyCreator.setUsePrefix(true);
		defaultAdvisorAutoProxyCreator.setAdvisorBeanNamePrefix("_no_advisor");
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

}
