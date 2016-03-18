/**
 * 
 */
package com.feng.ssh.answer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Answer及其实现类主要为了构造JSON字符串以及处理其层次关系，不参与业务逻辑实现
 * 
 * @author feng
 */
public interface Answer {
	void writeToMobile(OutputStream os, String charset) throws IOException;

}
