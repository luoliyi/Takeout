package com.vo;

public class UI {
	 /**��Ӧ����*/
    private int code;
    /**��Ӧ��Ϣ*/
    private String msg;
    /**��������*/
    private int count;
    /**����*/
    private Object data;
    public UI() {}
    
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "UI [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public UI(int code, String msg, int count, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
}
