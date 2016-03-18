package com.feng.ssh.answer;

/** 定义给客户端的返回结构,之后再抽象接口 */
public class ResultStruct {

	protected int code;
	protected String info;
	protected Object data;

	public ResultStruct() {
		super();
	}

	public ResultStruct(int code, String info, Object data) {
		this.code = code;
		this.info = info;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ResultStruct [code=" + code + ", info=" + info + ", data="
				+ data + "]";
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
