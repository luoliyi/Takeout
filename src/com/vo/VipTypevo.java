package com.vo;

/**
 * vipType bean
 * @author ´óË³
 *
 */
public class VipTypevo {

	private int vtid;
	private String vtname;
	private double vtdiscount;
	public int getVtid() {
		return vtid;
	}
	public void setVtid(int vtid) {
		this.vtid = vtid;
	}
	public String getVtname() {
		return vtname;
	}
	public void setVtname(String vtname) {
		this.vtname = vtname;
	}
	public double getVtdiscount() {
		return vtdiscount;
	}
	public void setVtdiscount(double vtdiscount) {
		this.vtdiscount = vtdiscount;
	}
	
	public VipTypevo(int vtid, String vtname, double vtdiscount) {
		super();
		this.vtid = vtid;
		this.vtname = vtname;
		this.vtdiscount = vtdiscount;
	}
	public VipTypevo(String vtname, double vtdiscount) {
		super();
		this.vtname = vtname;
		this.vtdiscount = vtdiscount;
	}
	public VipTypevo() {}
	
	@Override
	public String toString() {
		return "VipTypevo [vtid=" + vtid + ", vtname=" + vtname + ", vtdiscount=" + vtdiscount + "]";
	}
	
}
