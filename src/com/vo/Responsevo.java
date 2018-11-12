package com.vo;

import com.util.Json;

/**
 * response bean
 * @author 大顺
 *
 */
public class Responsevo {

	private int code;//响应状态编号 
	private String msg;//响应信息
	private Object data;//响应数据
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Responsevo(int code) {
		super();
		this.code = code;
	}
	public Responsevo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public Responsevo(int code,Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	public Responsevo(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Responsevo() {}
	
	public String serialize() {
		return Json.serialize(this);
	}
}
