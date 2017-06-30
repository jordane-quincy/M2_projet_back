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
	public void destroy() {
	}

	/**
	 * filtrage des requêtes via le token. Ajout des headers dans les reponses
	 * 
	 * @throws Exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (!response.containsHeader("Access-Control-Allow-Origin"))
			response.addHeader("Access-Control-Allow-Origin", "*");
		if (!response.containsHeader("Access-Control-Allow-Headers"))
			response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		if (!response.containsHeader("Access-Control-Allow-Methods"))
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");

		if (request.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		String path = request.getServletPath();
		System.out.println(path);
		System.out.println(path.contains("auth/connect"));
		// Pas de token nécessaire pour accéder à ces URI
		if (path.contains("auth/connect") || path.contains("user/create") || path.contains("formation/list")
				|| path.contains("skill/list") || path.contains("user/askResetPassword")
				|| path.contains("user/resetPassword") || path.contains("account_certification/certify")
				|| path.contains("/error")) {
			chain.doFilter(request, response);
		} else {
			System.out.println("Path requires token, validating");
			String token = request.getHeader("Authorization");
			try {
				tokenService.onRequest(token);
				chain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
