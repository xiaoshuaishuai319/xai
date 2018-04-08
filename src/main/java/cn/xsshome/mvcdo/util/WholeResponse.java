package cn.xsshome.mvcdo.util;

import java.util.Date;
/**
 * 全局响应对象
 * @author 小帅丶
 */
public class WholeResponse{
	private String logId;
	private String code;
	private String msg;
	
	public static WholeResponse errorResponse(String msg) {
		WholeResponse loginResponse = new WholeResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode("500");
		loginResponse.setMsg(msg);
		return loginResponse;
	}
	public static WholeResponse errorResponse(String code,String msg) {
		WholeResponse loginResponse = new WholeResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode(code);
		loginResponse.setMsg(msg);
		return loginResponse;
	}
	public static WholeResponse successResponse(String msg) {
		WholeResponse loginResponse = new WholeResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode("0");
		loginResponse.setMsg(msg);
		return loginResponse;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
