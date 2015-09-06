/**
 * 
 */
package com.feng.ssh.answer.base;

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

	/* 返回值为简单的标识结果的方法 */
	public void setListAnswer(Object answerlist, String info) {
		result = new ResultStruct(200, info, answerlist);
	}

	/* 返回值为键值对List的方法 */
	public void setSimpleAnswer(String data, String info) {
		result = new ResultStruct(200, info, data);
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
