/**
 * 
 */
package com.feng.ssh.answer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author feng
 *
 */
public class AbstractAnswer implements Answer {
	protected static final Log log = LogFactory.getLog(AbstractAnswer.class);

	protected ResultStruct result;

	/* 程序正常返回时的返回方法 */
	public void setFineAnswer(Object answer, String info) {
		result = new ResultStruct(200, info, answer);
	}

	/* 当程序遇到违例的时候的返回方法 */
	public void setExceptionAnswer(Exception e) {
		result = new ResultStruct(500, "failure", e.toString());
	}

	@Override
	public void writeToMobile(OutputStream os, String charset)
			throws IOException {
		String resultString = JSON.toJSONString(this.result);
		log.info("resultString : " + resultString);
		resultString = URLEncoder.encode(resultString, charset);
		os.write(resultString.getBytes());
	}

}
