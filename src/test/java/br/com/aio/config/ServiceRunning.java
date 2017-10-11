package br.com.aio.config;

import org.springframework.stereotype.Component;

@Component
public class ServiceRunning {

	private static int DEFAULT_PORT = 8086;

	private static String DEFAULT_HOST = "localhost";
	
	private static String CONTEXT_NAME = "/aio";
	
	public String getBaseUrl() {
		return new StringBuilder().append("http://").append(DEFAULT_HOST).append(":").append(DEFAULT_PORT).toString();
	}

	public String getUrl(String path) {
		if (path.startsWith("http:")) {
			return path;
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return new StringBuilder().append("http://").append(DEFAULT_HOST).append(":").append(DEFAULT_PORT).append(CONTEXT_NAME).append(path).toString();
	}
}
