package com.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Ordersvo {

	private Date oorderDate;
	private String oNumber;
	private BigDecimal omoney;
	private boolean oinvoice;
	private int ovid;
	private int osid;
	private String oremark;
	private int ostatus;
	
	public Date getOorderDate() {
		return oorderDate;
	}
	public void setOorderDate(Date oorderDate) {
		this.oorderDate = oorderDate;
	}
	public String getoNumber() {
		return oNumber;
	}
	public void setoNumber(String oNumber) {
		this.oNumber = oNumber;
	}
	public BigDecimal getOmoney() {
		return omoney;
	}
	public void setOmoney(BigDecimal omoney) {
		this.omoney = omoney;
	}
	public boolean isOinvoice() {
		return oinvoice;
	}
	public void setOinvoice(boolean oinvoice) {
		this.oinvoice = oinvoice;
	}
	public int getOvid() {
		return ovid;
	}
	public void setOvid(int ovid) {
		this.ovid = ovid;
	}
	public int getOsid() {
		return osid;
	}
	public void setOsid(int osid) {
		this.osid = osid;
	}
	public String getOremark() {
		return oremark;
	}
	public void setOremark(String oremark) {
		this.oremark = oremark;
	}
	public int getOstatus() {
		return ostatus;
	}
	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}
	@Override
	public String toString() {
		return "Ordersvo [oorderDate=" + oorderDate + ", oNumber=" + oNumber + ", omoney=" + omoney + ", oinvoice="
				+ oinvoice + ", ovid=" + ovid + ", osid=" + osid + ", oremark=" + oremark + ", ostatus=" + ostatus
				+ "]";
	}
	public Ordersvo(Date oorderDate, String oNumber, BigDecimal omoney, boolean oinvoice, int ovid, int osid,
			String oremark, int ostatus) {
		super();
		this.oorderDate = oorderDate;
		this.oNumber = oNumber;
		this.omoney = omoney;
		this.oinvoice = oinvoice;
		this.ovid = ovid;
		this.osid = osid;
		this.oremark = oremark;
		this.ostatus = ostatus;
	}
	
	public Ordersvo(){}
	
}
