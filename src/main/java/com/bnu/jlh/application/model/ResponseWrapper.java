package com.bnu.jlh.application.model;

import java.io.Serializable;

public class ResponseWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//返回代码
	private String returnCode="200";
	//返回中文信息
	private String returnMsg="执行成功";
	//返回对象
	private Object returnObj;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public Object getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	
}
