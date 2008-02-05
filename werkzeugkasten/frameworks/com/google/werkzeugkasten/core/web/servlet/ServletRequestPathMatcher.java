package com.google.werkzeugkasten.core.web.servlet;

import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.RequestMatcher;
import com.google.werkzeugkasten.core.web.servlet.util.ServletUtil;

public interface ServletRequestPathMatcher
		extends
		RequestMatcher<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Boolean>> {

	class RegEx implements ServletRequestPathMatcher {
		protected Pattern pattern;

		public RegEx(String path) {
			this.pattern = Pattern.compile(path);
		}

		public Boolean execute(ServletWebContext<Boolean> parameter) {
			String path = ServletUtil.getRequestPath(parameter.getRequest());
			if (this.pattern.matcher(path).matches()) {
				return parameter.execute();
			}
			return false;
		}
	}

	class Prefix implements ServletRequestPathMatcher {
		protected String prefix = null;

		public Prefix(String path) {
			this.prefix = path;
		}

		public Boolean execute(ServletWebContext<Boolean> parameter) {
			String path = ServletUtil.getRequestPath(parameter.getRequest());
			if (path.startsWith(this.prefix)) {
				return parameter.execute();
			}
			return false;
		}
	}

	class Suffix implements ServletRequestPathMatcher {
		protected String suffix = null;

		public Suffix(String path) {
			this.suffix = path;
		}

		public Boolean execute(ServletWebContext<Boolean> parameter) {
			String path = ServletUtil.getRequestPath(parameter.getRequest());
			if (path.endsWith(this.suffix)) {
				return parameter.execute();
			}
			return false;
		}
	}
}
