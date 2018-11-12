package com.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Vipvo {
	
	private int vid;
	private String vaccountNumber;
	private String vpassword;
	private int vvtid;
	private String vname;
	private String vsex;
	private String vheadPortrait;
	private Date vbirthday;
	private String vregion;
	private String vemail;
	private String vflavor;
	private String vpersonality;
	private int vintegral;
	private BigDecimal vbalance;
	private String vpaymentPassword;
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getVaccountNumber() {
		return vaccountNumber;
	}
	public void setVaccountNumber(String vaccountNumber) {
		this.vaccountNumber = vaccountNumber;
	}
	public String getVpassword() {
		return vpassword;
	}
	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}
	public int getVvtid() {
		return vvtid;
	}
	public void setVvtid(int vvtid) {
		this.vvtid = vvtid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVsex() {
		return vsex;
	}
	public void setVsex(String vsex) {
		this.vsex = vsex;
	}
	public String getVheadPortrait() {
		return vheadPortrait;
	}
	public void setVheadPortrait(String vheadPortrait) {
		this.vheadPortrait = vheadPortrait;
	}
	public Date getVbirthday() {
		return vbirthday;
	}
	public void setVbirthday(Date vbirthday) {
		this.vbirthday = vbirthday;
	}
	public String getVregion() {
		return vregion;
	}
	public void setVregion(String vregion) {
		this.vregion = vregion;
	}
	public String getVemail() {
		return vemail;
	}
	public void setVemail(String vemail) {
		this.vemail = vemail;
	}
	public String getVflavor() {
		return vflavor;
	}
	public void setVflavor(String vflavor) {
		this.vflavor = vflavor;
	}
	public String getVpersonality() {
		return vpersonality;
	}
	public void setVpersonality(String vpersonality) {
		this.vpersonality = vpersonality;
	}
	public int getVintegral() {
		return vintegral;
	}
	public void setVintegral(int vintegral) {
		this.vintegral = vintegral;
	}
	public BigDecimal getVbalance() {
		return vbalance;
	}
	public void setVbalance(BigDecimal vbalance) {
		this.vbalance = vbalance;
	}
	public String getVpaymentPassword() {
		return vpaymentPassword;
	}
	public void setVpaymentPassword(String vpaymentPassword) {
		this.vpaymentPassword = vpaymentPassword;
	}
	public Vipvo(int vid, String vaccountNumber, String vpassword, int vvtid, String vname, String vsex,
			String vheadPortrait, Date vbirthday, String vregion, String vemail, String vflavor, String vpersonality,
			int vintegral, BigDecimal vbalance, String vpaymentPassword) {
		super();
		this.vid = vid;
		this.vaccountNumber = vaccountNumber;
		this.vpassword = vpassword;
		this.vvtid = vvtid;
		this.vname = vname;
		this.vsex = vsex;
		this.vheadPortrait = vheadPortrait;
		this.vbirthday = vbirthday;
		this.vregion = vregion;
		this.vemail = vemail;
		this.vflavor = vflavor;
		this.vpersonality = vpersonality;
		this.vintegral = vintegral;
		this.vbalance = vbalance;
		this.vpaymentPassword = vpaymentPassword;
	}
	public Vipvo() {}
	public String serialize() {
		// TODO Auto-generated method stub
		return this.serialize();
	}
	
}
