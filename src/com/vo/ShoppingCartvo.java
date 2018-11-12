package com.vo;

import java.math.BigDecimal;

public class ShoppingCartvo {
	
	private int scid;
	private int scvip;
	private int scmenu;
	private String mname;
	private BigDecimal mprice;
	private int scquantity;
	
	public BigDecimal getMprice() {
		return mprice;
	}
	public void setMprice(BigDecimal mprice) {
		this.mprice = mprice;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public int getScvip() {
		return scvip;
	}
	public void setScvip(int scvip) {
		this.scvip = scvip;
	}
	public int getScmenu() {
		return scmenu;
	}
	public void setScmenu(int scmenu) {
		this.scmenu = scmenu;
	}
	public int getScquantity() {
		return scquantity;
	}
	public void setScquantity(int scquantity) {
		this.scquantity = scquantity;
	}
	public ShoppingCartvo() {}
	public ShoppingCartvo(int scid, int scquantity) {
		super();
		this.scid = scid;
		this.scquantity = scquantity;
	}
	public ShoppingCartvo( int scvip, int scmenu, int scquantity) {
		super();
		this.scvip = scvip;
		this.scmenu = scmenu;
		this.scquantity = scquantity;
	}
	public ShoppingCartvo(int scid, int scvip, int scmenu, int scquantity) {
		super();
		this.scid = scid;
		this.scvip = scvip;
		this.scmenu = scmenu;
		this.scquantity = scquantity;
	}
	
}
