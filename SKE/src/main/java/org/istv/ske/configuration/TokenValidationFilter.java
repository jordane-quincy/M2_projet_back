package org.istv.ske.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.HttpParser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TokenValidationFilter implements Filter {
	
	private JsonParser parser = new JsonParser();

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			JsonObject content = parser.parse(request.getReader()).getAsJsonObject();
			final String token = content.get("token").getAsString();
			
			if(token.length() > 6)
				chain.doFilter(request, response);
			else
				throw new RuntimeException("");
			
		} catch(Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Token invalide");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
