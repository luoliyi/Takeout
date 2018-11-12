package com.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Menuvo {
	
	private int mid;
	private int mmtid;
	private String mname;
	private BigDecimal mprice;
	private BigDecimal mdiscount;
	private Date mdate;
	private boolean mstatus;
	
	
	public int getMid() {
		return mid;
	}


	public void setMid(int mid) {
		this.mid = mid;
	}


	public int getMmtid() {
		return mmtid;
	}


	public void setMmtid(int mmtid) {
		this.mmtid = mmtid;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public BigDecimal getMprice() {
		return mprice;
	}


	public void setMprice(BigDecimal mprice) {
		this.mprice = mprice;
	}


	public BigDecimal getMdiscount() {
		return mdiscount;
	}


	public void setMdiscount(BigDecimal mdiscount) {
		this.mdiscount = mdiscount;
	}


	public Date getMdate() {
		return mdate;
	}


	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}


	public boolean isMstatus() {
		return mstatus;
	}


	public void setMstatus(boolean mstatus) {
		this.mstatus = mstatus;
	}


	public Menuvo(int mid, int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		super();
		this.mid = mid;
		this.mmtid = mmtid;
		this.mname = mname;
		this.mprice = mprice;
		this.mdiscount = mdiscount;
		this.mdate = mdate;
		this.mstatus = mstatus;
	}
	public Menuvo(int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		super();
		this.mmtid = mmtid;
		this.mname = mname;
		this.mprice = mprice;
		this.mdiscount = mdiscount;
		this.mdate = mdate;
		this.mstatus = mstatus;
	}

	public Menuvo() {}
	
}
