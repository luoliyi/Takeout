package com.vo;

public class Consumptionvo {

	private int cid;
	private String coNumber;
	private int cmid;
	private int cquantity;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCoNumber() {
		return coNumber;
	}

	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
	}

	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public int getCquantity() {
		return cquantity;
	}

	public void setCquantity(int cquantity) {
		this.cquantity = cquantity;
	}

	public Consumptionvo() {}

	public Consumptionvo(int cid, String coNumber, int cmid, int cquantity) {
		super();
		this.cid = cid;
		this.coNumber = coNumber;
		this.cmid = cmid;
		this.cquantity = cquantity;
	}
	
}
