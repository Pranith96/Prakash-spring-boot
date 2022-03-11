package com.online.ecommerce.application.util;

import java.text.MessageFormat;

public class ServiceUrlBuilder {

	private ServiceUrlBuilder() {
	}

	public static String constructUrl(String serviceId, Integer port, String contextPath, String pathVariable) {
		if (contextPath.startsWith("/")) {
			contextPath = contextPath.replaceFirst("/", "");
		}

		if (pathVariable == null) {
			pathVariable = "";
			if (contextPath.endsWith("/")) {
				contextPath = contextPath.substring(0, contextPath.length() - 1);
			}
		} else {
			if (!contextPath.endsWith("/")) {
				contextPath = contextPath.concat("/");
			}
		}

		return MessageFormat.format("http://{0}:{1}/{2}{3}", serviceId, String.valueOf(port), contextPath,
				pathVariable);
	}
}