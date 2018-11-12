package com.vo;

public class MenuTypevo {
	
	private int mtid;
	private String mtname;
	private int mtParentClass;
	private Object subclass;
	public int getMtid() {
		return mtid;
	}
	public void setMtid(int mtid) {
		this.mtid = mtid;
	}
	public String getMtname() {
		return mtname;
	}
	public void setMtname(String mtname) {
		this.mtname = mtname;
	}
	public int getMtParentClass() {
		return mtParentClass;
	}
	public void setMtParentClass(int mtParentClass) {
		this.mtParentClass = mtParentClass;
	}
	public Object getSubclass() {
		return subclass;
	}
	public void setSubclass(Object subclass) {
		this.subclass = subclass;
	}
	public MenuTypevo() {}
	public MenuTypevo(String mtname, int mtParentClass) {
		super();
		this.mtname = mtname;
		this.mtParentClass = mtParentClass;
	}
	public MenuTypevo(int mtid, String mtname) {
		super();
		this.mtid = mtid;
		this.mtname = mtname;
	}
	public MenuTypevo(int mtid, String mtname, int mtParentClass) {
		super();
		this.mtid = mtid;
		this.mtname = mtname;
		this.mtParentClass = mtParentClass;
	}
}
