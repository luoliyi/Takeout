package Zcom.bos;

import java.util.Date;
import java.util.List;

import Zcom.daos.Menudaoimpl;
import Zcom.vos.Menu;

public class Menuboimpl {
	Menudaoimpl md=new Menudaoimpl();
	public List<Menu> getMenuAllListAll(){
		return md.getMenuAllListAll();
	}
	public List<Menu> getMenuAllList(int mmtid){
		return md.getMenuAllList(mmtid);
	}
	public int deleteMenu(int mid) {
		return md.deleteMenu(mid);
	}
	public int updateMenu(int mmtid,String mname,float mprice,float mdiscount,Date mdate,int mstatus,int mid) {
		return md.updateMenu(mmtid, mname, mprice, mdiscount, mdate, mstatus, mid);
	}
	public int insertMenu(int mmtid,String mname,float mprice,float mdiscount,Date mdate,int mstatus) {
		return md.insertMenu(mmtid, mname, mprice, mdiscount, mdate, mstatus);
	}
}
