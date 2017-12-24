package com.qg.fangrui.sso;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 启动主类
 * @author FunriLy
 * Created by FunriLy on 2017/12/23.
 * From small beginnings comes great things.
 */
@SpringBootApplication
public class SingleSignOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleSignOnApplication.class, args);
	}


	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public KeycloakSecurityContext getKeycloakSecurityContext() {

		/*
		1、WebApplicationContext.SCOPE_REQUEST 可以使用独立注解'@RequestScope'，将一个组件分配到request作用域
		可以说就是在一个全球中创建一个实例
		2、ScopedProxyMode.TARGET_CLASS Spring IOC 指定基于类的代理模式(CGLIB)
		 */

		return ((KeycloakPrincipal)getRequest().getUserPrincipal()).getKeycloakSecurityContext();
	}

	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public AccessToken getAccessToken() {
		// 同理
		return ((KeycloakPrincipal) getRequest().getUserPrincipal()).getKeycloakSecurityContext().getToken();
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
}
