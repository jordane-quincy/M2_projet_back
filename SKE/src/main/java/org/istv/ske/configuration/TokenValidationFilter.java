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

public class TokenValidationFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		try {
			String token = request.getHeader("Authorization");
			
			if(token == null)
				throw new RuntimeException("Aucun token fourni");
			
			if(token.length() < 6)
				throw new RuntimeException("Le token fourni est invalide");

			chain.doFilter(request, response);

		} catch(Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
