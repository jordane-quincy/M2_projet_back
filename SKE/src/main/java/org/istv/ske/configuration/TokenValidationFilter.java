package org.istv.ske.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.istv.ske.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenValidationFilter implements Filter {

	@Autowired
	private TokenService tokenService;
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		
		System.out.println(request.getContextPath());
		if(request.getServletPath().equals("/auth/connect") || 
		   request.getServletPath().equals("/user/create") ||
		   request.getServletPath().equals("/formation/list") ||
		   request.getServletPath().equals("/skill/list") ||
		   request.getServletPath().equals("/user/askResetPassword") ||
		   request.getServletPath().equals("/user/resetPassword")
		   ) {
			chain.doFilter(request, response);
			return;
		}
		
		try {
			String token = request.getHeader("Authorization");
			tokenService.onRequest(token);
			chain.doFilter(request, response);
		} catch(Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
