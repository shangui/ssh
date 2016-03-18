/**
 * 
 */
package com.feng.ssh.pojo;

/**
 * @author Administrator 2016年3月17日
 *
 */
public class User {

	public int DataId;
	public String UserName;
	public String Phone;
	public String Password;

	/**
	 * @return the dateId
	 */
	public int getDataId() {
		return DataId;
	}

	/**
	 * @param dateId
	 *            the dateId to set
	 */
	public void setDataId(int dataId) {
		DataId = dataId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return Phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

}
