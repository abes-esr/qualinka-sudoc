package fr.abes.qualinka.ws.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * This servlet Filter sets the contentType and
 * the encoding in HTTP headers.
 * 
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public class ContentTypeAndEncodingFilter implements Filter {

	private FilterConfig config;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		String contentType = this.config.getInitParameter("contentType");
		if(contentType == null) {
			contentType = "application/json";
		}
		res.setContentType(contentType);
		
		String encoding = this.config.getInitParameter("encoding");
		if(encoding == null) {
			encoding = "UTF-8";
		}
		res.setCharacterEncoding(encoding);
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void destroy() {
		// nothing to do		
	}
	
}