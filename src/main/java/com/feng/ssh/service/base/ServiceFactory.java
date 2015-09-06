package com.feng.ssh.service.base;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceFactory {

	private static Map<String, Service> services;

	private static Log log = LogFactory.getLog(ServiceFactory.class);

	/**
	 * 根据at参数请求分发服务
	 */
	public static Service getService(Map<String, Object> params)
			throws Exception {
		String apiType = (String) params.get("at");
		String serviceVersion = (String) params.get("vs");

		if (apiType == null || serviceVersion == null) {
			throw new Exception("missing apiType or serviceVersion.");
		}
		Service service = services.get(apiType + "_" + serviceVersion);
		if (service == null) {
			throw new Exception("unknown service : " + apiType + "_"
					+ serviceVersion);
		}
		log.debug("apiType: " + apiType + ", version : " + serviceVersion
				+ ", service : " + service.getClass());
		return service;
	}

	/** 从spring-service.xml文件注入的Service */
	public static void setServices(Map<String, Service> serviceMap) {
		log.debug("注入的服务： " + serviceMap.size() + ",  detail : " + serviceMap);
		services = serviceMap;
	}

}
