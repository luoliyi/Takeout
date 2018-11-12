package com.vo;

public class Locationsvo {

	private int lid;
	private int lvid;
	private String lname;
	private String lsex;
	private String lphone;
	private String laddress;
	private boolean lstatus;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getLvid() {
		return lvid;
	}
	public void setLvid(int lvid) {
		this.lvid = lvid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLsex() {
		return lsex;
	}
	public void setLsex(String lsex) {
		this.lsex = lsex;
	}
	public String getLphone() {
		return lphone;
	}
	public void setLphone(String lphone) {
		this.lphone = lphone;
	}
	public String getLaddress() {
		return laddress;
	}
	public void setLaddress(String laddress) {
		this.laddress = laddress;
	}
	public boolean isLstatus() {
		return lstatus;
	}
	public void setLstatus(boolean lstatus) {
		this.lstatus = lstatus;
	}
	public Locationsvo() {}
	public Locationsvo(int lvid, String lname, String lsex, String lphone, String laddress, boolean lstatus) {
		super();
		this.lvid = lvid;
		this.lname = lname;
		this.lsex = lsex;
		this.lphone = lphone;
		this.laddress = laddress;
		this.lstatus = lstatus;
	}
	public Locationsvo(int lid, int lvid, String lname, String lsex, String lphone, String laddress, boolean lstatus) {
		super();
		this.lid = lid;
		this.lvid = lvid;
		this.lname = lname;
		this.lsex = lsex;
		this.lphone = lphone;
		this.laddress = laddress;
		this.lstatus = lstatus;
	}
	
}
