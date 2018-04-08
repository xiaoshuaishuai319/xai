package cn.xsshome.mvcdo.util;

import java.util.Date;
/**
 * 登录响应对象
 * @author 小帅丶
 */
public class LoginResponse{
	private String logId;
	private String code;
	private String msg;
	
	public static LoginResponse errorLogin(String msg) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode("500");
		loginResponse.setMsg(msg);
		return loginResponse;
	}
	public static LoginResponse errorLogin(String code,String msg) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode(code);
		loginResponse.setMsg(msg);
		return loginResponse;
	}
	public static LoginResponse successLogin() {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setLogId(new Date().getTime()/1000+"");
		loginResponse.setCode("0");
		loginResponse.setMsg("登录成功");
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
