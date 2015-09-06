/**
 * 
 */
package com.feng.ssh.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.feng.ssh.answer.base.Answer;
import com.feng.ssh.service.base.Service;
import com.feng.ssh.service.base.ServiceFactory;
import com.feng.ssh.util.RequestInfoUtil;

/**
 * 来自客户端的请求入口均为 localhost:8080/supernove/mbclient
 * 
 * @author feng
 *
 */
public class ClientFacadeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doService(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doService(req, res);
	}

	public void init() throws ServletException {
	}

	public void destroy() {
	}

	/* 此处主要做流程控制 */
	private void doService(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();

		log.debug("----" + new Timestamp(start).toString() + "---"
				+ req.getLocalAddr() + ":" + req.getLocalPort());

		// 将所有备用信息统一存储在参数列表中
		// 演化过程中再考量是否应该不这么做
		Map<String, Object> requestInfoMap = RequestInfoUtil
				.extractRequest(req);
		if (requestInfoMap == null) {
			log.error("paramsmap == null");
		} else {
			log.info("params map.size() == " + requestInfoMap.size());
		}

		// 从客户端上传参数中解析出params对应的JSON字符串
		Map<String, Object> clientParams = RequestInfoUtil
				.extractParamsMap(requestInfoMap);
		// 更加详细的客户端信息用作数据挖掘

		if (clientParams == null) {
			clientParams = requestInfoMap;
		}

		try {
			// 1加密
			// 2分发
			// 3处理接口版本升级的问题,在不升级客户端的情况下兼容服务器接口

			Service service = ServiceFactory.getService(clientParams);
			log.debug("ServiceFactory getService: " + service.getClass());
			// 4 以后可能是一个更加复杂的工厂模式，解析JSON/字节流/XML
			// 演示 返回一个空的JSON字符串
			Answer answer = service.getAnswer(clientParams);

			if (answer == null) {
				log.debug("answer is null");
			}
			log.debug(" service.getAnswer: " + answer.getClass());

			writeAnswer(answer, res, null);

			long end = System.currentTimeMillis();
			log.debug((end - start) + "ms, params: " + clientParams
					+ ", answer : " + answer);
			log.debug("----" + new Timestamp(end).toString() + "---"
					+ req.getLocalAddr() + ":" + req.getLocalPort()
					+ " 的日志:一次请求结束----");
		} catch (Exception e) {
			String errorInfo = "";
			log.info(errorInfo, e);
			e.printStackTrace();
		} finally {
			// 最后一定关闭输入输出流
			res.getOutputStream().close();
		}
	}

	private static void writeAnswer(Answer answer, ServletResponse response,
			String charset) throws IOException {
		if (charset == null) {
			charset = "utf-8";
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		answer.writeToMobile(bos, charset);

		int contentLen = bos.size();
		if (response.getBufferSize() < contentLen)
			response.setBufferSize(contentLen);
		response.setContentLength(bos.size());
		bos.writeTo(response.getOutputStream());
		bos.close();
	}

	private static final Log log = LogFactory.getLog(ClientFacadeServlet.class);

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1974374438635039751L;

}
