package Zcom.vos;

import java.util.Date;

public class Menus {
	int mid;
	String mmtid;
	String mname;
	float mprice;
	float mdiscount;
	Date mdate;
	String status;
	public Menus() {}
	public Menus(int mid, String mmtid, String mname, float mprice, float mdiscount, Date mdate, String status) {
		super();
		this.mid = mid;
		this.mmtid = mmtid;
		this.mname = mname;
		this.mprice = mprice;
		this.mdiscount = mdiscount;
		this.mdate = mdate;
		this.status = status;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMmtid() {
		return mmtid;
	}
	public void setMmtid(String mmtid) {
		this.mmtid = mmtid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public float getMprice() {
		return mprice;
	}
	public void setMprice(float mprice) {
		this.mprice = mprice;
	}
	public float getMdiscount() {
		return mdiscount;
	}
	public void setMdiscount(float mdiscount) {
		this.mdiscount = mdiscount;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
