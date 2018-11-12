package com.vo;

import com.util.Json;

/**
 * response bean
 * @author ��˳
 *
 */
public class Responsevo {

	private int code;//��Ӧ״̬��� 
	private String msg;//��Ӧ��Ϣ
	private Object data;//��Ӧ����
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
