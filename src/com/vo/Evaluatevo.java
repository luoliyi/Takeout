package com.vo;

public class Evaluatevo {

	private int eid;
	private String eoNumber;
	private double eminute;
	private String eremark;
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEoNumber() {
		return eoNumber;
	}

	public void setEoNumber(String eoNumber) {
		this.eoNumber = eoNumber;
	}

	public double getEminute() {
		return eminute;
	}

	public void setEminute(double eminute) {
		this.eminute = eminute;
	}

	public String getEremark() {
		return eremark;
	}

	public void setEremark(String eremark) {
		this.eremark = eremark;
	}

	public Evaluatevo(int eid, String eoNumber, double eminute, String eremark) {
		super();
		this.eid = eid;
		this.eoNumber = eoNumber;
		this.eminute = eminute;
		this.eremark = eremark;
	}
	public Evaluatevo( String eoNumber, double eminute, String eremark) {
		super(); 
		this.eoNumber = eoNumber;
		this.eminute = eminute;
		this.eremark = eremark;
	}
	public Evaluatevo() {}
}
