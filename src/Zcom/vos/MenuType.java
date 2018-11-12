package Zcom.vos;

public class MenuType {
	int mtid;
	String mtname;
	int mtParentClass;
	public MenuType() {}
	public MenuType(int mtid, String mtname, int mtParentClass) {
		super();
		this.mtid = mtid;
		this.mtname = mtname;
		this.mtParentClass = mtParentClass;
	}
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
	
}
