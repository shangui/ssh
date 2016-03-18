/**
 * 
 */
package com.feng.ssh.service.common;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.feng.ssh.answer.AbstractAnswer;
import com.feng.ssh.answer.Answer;
import com.feng.ssh.hibernate.HbmBaseDAO;
import com.feng.ssh.pojo.User;
import com.feng.ssh.service.base.Service;

/**
 * @author Administrator 2016年3月17日
 *
 */
@Transactional
public class TestService implements Service {
	public HbmBaseDAO hbmBaseDAO;

	/**
	 * @return the hbmBaseDAO
	 */
	public HbmBaseDAO getHbmBaseDAO() {
		return hbmBaseDAO;
	}

	/**
	 * @param hbmBaseDAO
	 *            the hbmBaseDAO to set
	 */
	public void setHbmBaseDAO(HbmBaseDAO hbmBaseDAO) {
		this.hbmBaseDAO = hbmBaseDAO;
	}

	@Override
	public Answer getAnswer(Map<String, Object> params) throws Exception {
		String userName = (String) params.get("userName");
		String password = (String) params.get("password");
		String phone = (String) params.get("phone");
		AbstractAnswer answer = new AbstractAnswer();
		User user = new User();
		user.setPassword(password);
		user.setUserName(userName);
		user.setPhone(phone);
		try {
			hbmBaseDAO.save(user);
		} catch (Exception e) {
			answer.setExceptionAnswer(e);
		}
		answer.setFineAnswer("OK", "保存成功");
		return answer;
	}
}
