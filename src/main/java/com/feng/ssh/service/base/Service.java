package com.feng.ssh.service.base;

import java.util.Map;

import com.feng.ssh.answer.Answer;

/* 后续再拆分Model层 */
public interface Service {

	/* 传递JSON字符串作为参数 */
	Answer getAnswer(Map<String, Object> params) throws Exception;
}
