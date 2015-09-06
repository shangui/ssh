package com.feng.ssh.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @author shengl 用来解析所有的request请求参数 REQUEST参数详解 <br>
 *         getAttribute(String name)：返回由name指定的属性值<br>
 *         getAttributeNames()：返回request对象所有属性的名字集合，结果是一个枚举的实例<br>
 *         getCharacterEncoding()：返回请求中的字符编码方式 <br>
 *         getContentLength()：返回请求的Body的长度<br>
 *         getCookies()：返回客户端的所有Cookie对象，结果是一个Cookie数组 <br>
 *         getHeader(String name)：获得HTTP协议定义的文件头信息 <br>
 *         getHeaderNames()：返回所以request Header的名字，结果是一个枚举的实例<br>
 *         getHeaders(String name)：返回指定名字的request Header的所有值，结果是一个枚举的实例<br>
 *         getInputStream()：返回请求的输入流，用于获得请求中的数据 <br>
 *         getMethod()：获得客户端向服务器端传送数据的方法<br>
 *         getParameter(String name)：获得客户端传送给服务器端的有name指定的参数值<br>
 *         getParameterNames()：获得客户端传送给服务器端的所有参数的名字，结果是一个枚举的实例 <br>
 *         getParameterValues(String name)：获得有name指定的参数的所有值 <br>
 *         getProtocol()：获取客户端向服务器端传送数据所依据的协议名称<br>
 *         getQueryString()：获得查询字符串 <br>
 *         getRemoteAddr()：获取客户端的IP地址 <br>
 *         getRemoteHost()：获取客户端的名字<br>
 *         getRequestURI()：获取发出请求字符串的客户端地址 <br>
 *         getServerName()：获取服务器的名字 <br>
 *         getServerPort()：获取服务器的端口号 <br>
 *         getServletPath()：获取客户端所请求的脚本文件的路径<br>
 *         getSession([Boolean create])：返回和请求相关Session <br>
 *         removeAttribute(String name)：删除请求中的一个属性 <br>
 *         setAttribute(String name,Object)：设置名字为name的request的参数值<br>
 */
public class RequestInfoUtil {

	private static final Log log = LogFactory.getLog(RequestInfoUtil.class);

	/** 全面解析HttpRequest */
	public static String requestAnalytical(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();

		// header
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();
		sb.append("------Header------\n");
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();

			Object value = request.getHeader(name);
			sb.append(name).append(":").append(value).append("\n");
		}

		// attribute
		@SuppressWarnings("unchecked")
		Enumeration<String> attributesNames = request.getAttributeNames();
		sb.append("------Attribute------\n");
		while (attributesNames.hasMoreElements()) {
			String name = attributesNames.nextElement();

			Object value = request.getAttribute(name);
			sb.append(name).append(":").append(value).append("\n");
		}

		// params
		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		sb.append("------Params------\n");
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();

			Object value = request.getParameter(name);
			sb.append(name).append(":").append(value).append("\n");
		}

		sb.append("------Others------\n");
		sb.append("Class()").append(":").append(request.getClass())
				.append("\n");
		sb.append("CharacterEncoding()").append(":")
				.append(request.getCharacterEncoding()).append("\n");
		sb.append("AuthType()").append(":").append(request.getAuthType())
				.append("\n");
		sb.append("ContentLength()").append(":")
				.append(request.getContentLength()).append("\n");
		sb.append("ContentType()").append(":").append(request.getContentType())
				.append("\n");
		sb.append("ContextPath()").append(":").append(request.getContextPath())
				.append("\n");
		sb.append("DateHeader()").append(":")
				.append(request.getDateHeader("If-Modified-Since"))
				.append("\n");
		sb.append("LocalAddr()").append(":").append(request.getLocalAddr())
				.append("\n");
		sb.append("LocalName()").append(":").append(request.getLocalName())
				.append("\n");
		sb.append("LocalPort()").append(":").append(request.getLocalPort())
				.append("\n");
		sb.append("Locale()").append(":").append(request.getLocale())
				.append("\n");
		sb.append("Locales()").append(":")
				.append(request.getLocales().toString()).append("\n");
		sb.append("Method()").append(":").append(request.getMethod())
				.append("\n");
		sb.append("PathInfo()").append(":").append(request.getPathInfo())
				.append("\n");
		sb.append("PathTranslated()").append(":")
				.append(request.getPathTranslated()).append("\n");
		sb.append("Protocol()").append(":").append(request.getProtocol())
				.append("\n");
		sb.append("QueryString()").append(":").append(request.getQueryString())
				.append("\n");
		sb.append("RemoteAddr()").append(":").append(request.getRemoteAddr())
				.append("\n");
		sb.append("RemoteHost()").append(":").append(request.getRemoteHost())
				.append("\n");
		sb.append("RemotePort()").append(":").append(request.getRemotePort())
				.append("\n");
		sb.append("RemoteUser()").append(":").append(request.getRemoteUser())
				.append("\n");
		sb.append("RequestedSessionId()").append(":")
				.append(request.getRequestedSessionId()).append("\n");
		sb.append("RequestURI()").append(":").append(request.getRequestURI())
				.append("\n");
		sb.append("RequestURL()").append(":")
				.append(request.getRequestURL().toString()).append("\n");
		sb.append("Scheme()").append(":").append(request.getScheme())
				.append("\n");
		sb.append("ServerName()").append(":").append(request.getServerName())
				.append("\n");
		sb.append("ServerPort()").append(":").append(request.getServerPort())
				.append("\n");
		sb.append("ServletPath()").append(":").append(request.getServletPath())
				.append("\n");
		sb.append("Session()").append(":").append(request.getSession().getId())
				.append("\n");
		sb.append("UserPrincipal()").append(":")
				.append(request.getUserPrincipal()).append("\n");

		log.debug("requestAnalytical(req): " + sb.toString());
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static String refalectCookie(Cookie cookie) {
		StringBuffer sb = new StringBuffer();
		Class<?> clazz = Cookie.class;
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			try {
				// 注意!! 由于Cookie的方法本身都没有参数，所以这么写没问题，不能类推到其他的类
				sb.append(m.getName()).append(":").append(m.invoke(cookie, ""))
						.append(",");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	/** 解析request */
	public static String requestInfo(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("RequestInfo:\n");
		sb.append("info{character=").append(request.getCharacterEncoding());
		sb.append(", length=").append(request.getContentType());
		sb.append("}");
		sb.append("client{ RemoteClientIP=").append(request.getRemoteAddr());
		sb.append(", RemoteHost=").append(request.getRemoteHost())
				.append(", RequestURI=");
		sb.append(request.getRequestURI()).append("}\n server{ ServerName=")
				.append(request.getServerName());
		sb.append(", ServletPort=").append(request.getServerPort())
				.append(", ServletPath");
		sb.append(request.getServletPath()).append("}\n");
		sb.append("]");
		return sb.toString();
	}

	/** 将request的请求解析成为一个参数列表,这里是Get的方式 */
	public static Map<String, String> extractParameters(ServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> keys = (Enumeration<String>) request
				.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			// 如果同一个参数有多个值，将只取第一个
			String value = request.getParameter(key);
			params.put(key, value);
		}
		return params;
	}

	/**
	 * 解析出客户端传递的POST表单中的参数 我们规定的是JSON字符串 以后可能是字节流,此时应该使用新的架构,在表单中必须告知服务器端传递参数的类型
	 */
	public static JSONObject extractPostInfo(ServletRequest request) {
		try {
			InputStream in = request.getInputStream();
			String result = IOUtils.toString(in, "UTF-8");
			log.info("params : " + result);
			JSONObject params = JSONObject.parseObject(result);
			log.info("post info : " + params);

			return params;
		} catch (IOException e) {
			log.info("解析参数出错了: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析出客户端发送的请求信息，如果需要修改参数名称，请在此标注说明 如 X-Forwarded-For => clientIP.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> extractRequest(HttpServletRequest request) {
		Map<String, Object> requestInfoMap = new HashMap<String, Object>();
		try {
			log.info("extract params starting......");

			// header
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();

				Object headerValue = request.getHeader(name);
				log.info("head : " + name + "=" + headerValue);
				requestInfoMap.put(name, headerValue);
			}

			// attribute
			Enumeration<String> attributesNames = request.getAttributeNames();
			while (attributesNames.hasMoreElements()) {
				String name = attributesNames.nextElement();

				Object attrValue = request.getAttribute(name);
				log.info("attribute：" + name + "=" + attrValue);
				requestInfoMap.put(name, attrValue);
			}

			// params, 演示的时候我们解析参数，但是真正使用的时候不允许这么传递参数
			// 这里放开现在是为了方便测试，后面我们要关闭此处，并且关闭doGet()方法
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = (String) paramNames.nextElement();

				Object paramValue = request.getParameter(name);
				log.info("xml文件为" + name + "=" + paramValue);
				requestInfoMap.put(name, paramValue);
				requestInfoMap.put("params=", name + "=" + paramValue);
			}
			log.info("extract params closing while paramsMap = "
					+ requestInfoMap.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestInfoMap; // 这里一定要保证返回，哪怕是个空的
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRequestXml(HttpServletRequest request) {
		Map<String, Object> requestInfoMap = new HashMap<String, Object>();
		try {
			log.info("extract params starting......");

			// header
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();

				Object headerValue = request.getHeader(name);
				log.info("head : " + name + "=" + headerValue);
				requestInfoMap.put(name, headerValue);
			}

			// attribute
			Enumeration<String> attributesNames = request.getAttributeNames();
			while (attributesNames.hasMoreElements()) {
				String name = attributesNames.nextElement();

				Object attrValue = request.getAttribute(name);
				log.info("attribute：" + name + "=" + attrValue);
				requestInfoMap.put(name, attrValue);
			}

			// params, 演示的时候我们解析参数，但是真正使用的时候不允许这么传递参数
			// 这里放开现在是为了方便测试，后面我们要关闭此处，并且关闭doGet()方法
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = (String) paramNames.nextElement();

				Object paramValue = request.getParameter(name);
				log.info("xml文件为" + name + "=" + paramValue);
				requestInfoMap.put(name, paramValue);
				requestInfoMap.put("params", name + "=" + paramValue);
			}
			log.info("extract params closing while paramsMap = "
					+ requestInfoMap.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestInfoMap; // 这里一定要保证返回，哪怕是个空的
	}

	/**
	 * 根据客户端请求信息获取参数 特别重要的约定，客户端往服务器传递的参数一定是这样设计的, 只对params做URLdecode.
	 */
	public static Map<String, Object> extractParamsMap(
			Map<String, Object> requestInfoMap) {
		// 特别重要的约定，客户端往服务器传递的参数一定是这样设计的,
		try {
			String paramsString = (String) requestInfoMap.get("params");
			String decodeParams = URLDecoder.decode(paramsString, "utf-8");
			JSONObject params = JSONObject.parseObject(decodeParams);
			log.info("客户端提交的参数是 Params: " + params);
			return params;
		} catch (Exception e) {
			log.error("Fatal Exception while requestInfoMap: " + requestInfoMap);
			e.printStackTrace();
		}
		return null;
	}

	public static String getXml(Map<String, Object> requestInfoMap) {
		// 特别重要的约定，客户端往服务器传递的参数一定是这样设计的,
		try {
			String paramsString = (String) requestInfoMap.get("params");
			// String decodeParams = URLDecoder.decode(paramsString, "utf-8");
			log.info("客户端提交的参数是 Params: " + paramsString);
			return paramsString;
		} catch (Exception e) {
			log.error("Fatal Exception while requestInfoMap: " + requestInfoMap);
			e.printStackTrace();
		}
		return null;
	}

	/** 解析出来Requset中的参数 */
	@SuppressWarnings("unchecked")
	public static void invoke(HttpServletRequest request) {
		try {
			log.info("invoke starting......");
			Enumeration<String> AttributesNames = request.getAttributeNames();
			Map<String, String> requestParams = new HashMap<String, String>();

			// header
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();

				Object headerValue = request.getHeader(name);
				log.info("head : " + name + "=" + headerValue);
			}

			// attribute
			while (AttributesNames.hasMoreElements()) {
				String name = AttributesNames.nextElement();

				Object attrValue = request.getAttribute(name);
				log.info("attribute：" + name + "=" + attrValue);
			}

			// params
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();

				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() != 0) {
						log.info("param：" + paramName + "=" + paramValue);
						requestParams.put(paramName, paramValue);
					}
				}
			}
			log.info("invoke closing......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
