/**
 * 
 */
package com.feng.ssh.test;

import com.feng.ssh.util.HttpClientUtil;

/**
 * @author Administrator 2016年3月17日
 *
 */
public class Test {
	public static void main(String[] args) {
		String URL = "http://localhost:8080/ssh/mbclient";
		String params = "{\"at\":\"test\",\"vs\":\"14\",\"userName\":\"test\",\"phone\":\"14278923455\",\"password\":\"123456\"}";
		params = "params=" + params;
		System.out.println(HttpClientUtil.post(URL, params));
	}
}
