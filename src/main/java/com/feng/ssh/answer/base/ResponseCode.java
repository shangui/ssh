package com.feng.ssh.answer.base;

/** 自定义的返回值 */
public class ResponseCode {

	public int code;
	public String desc;

	public ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ResponseCode))
			return false;
		return (this.code == ((ResponseCode) obj).code && this.desc
				.equals(((ResponseCode) obj).desc));
	}

	@Override
	public String toString() {
		return "ResponseCode [code=" + code + ", desc=" + desc + "]";
	}
	
	public static final ResponseCode SUCCESSED = new ResponseCode(200, "成功");
	public static final ResponseCode SERVER_ERROR =  new ResponseCode(500, "服务器开小差了");
	public static final ResponseCode SERVER_ERROR_OUTAGE =  new ResponseCode(50001, "服务器宕机了");

}
