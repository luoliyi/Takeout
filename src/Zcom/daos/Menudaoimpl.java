package Zcom.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Zcom.vos.Menu;

import util.DBUtil;

public class Menudaoimpl {
	//查询所有
	public List<Menu> getMenuAllListAll(){
		String sql="select *from Menu";
		ResultSet rs=(ResultSet)DBUtil.executeQuery(sql);
		List<Menu> list=new ArrayList<Menu>();
		Menu model=null;
		try {
			while(rs.next()) {
				model=new Menu();
				model.setMid(rs.getInt(1));
				model.setMmtid(rs.getInt(2));
				model.setMname(rs.getString(3));
				model.setMprice(rs.getFloat(4));
				model.setMdiscount(rs.getFloat(5));
				model.setMdate(rs.getDate(6));
				model.setStatus(rs.getInt(7));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//查询单个
	public List<Menu> getMenuAllList(int mmtid){
		String sql="select *from Menu where mmtid=?";
		ResultSet rs=(ResultSet)DBUtil.executeQuery(sql, new Object[] {mmtid});
		List<Menu> list=new ArrayList<Menu>();
		Menu model=null;
		try {
			while(rs.next()) {
				model=new Menu();
				model.setMid(rs.getInt(1));
				model.setMmtid(rs.getInt(2));
				model.setMname(rs.getString(3));
				model.setMprice(rs.getFloat(4));
				model.setMdiscount(rs.getFloat(5));
				model.setMdate(rs.getDate(6));
				model.setStatus(rs.getInt(7));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//删除菜名
	public int deleteMenu(int mid) {
		String sql="delete from Menu where mid=?";
		return DBUtil.executeUpdate(sql, new Object[] {mid});
	}
	//修改菜名
	public int updateMenu(int mmtid,String mname,float mprice,float mdiscount,Date mdate,int mstatus,int mid) {
		String sql="update Menu set mmtid=?,mname=?,mprice=?,mdiscount=?,mdate=?,mstatus=? where mid=?";
		return DBUtil.executeUpdate(sql, new Object[] {mmtid,mname,mprice,mdiscount,mdate,mstatus,mid});
	}
	//添加菜名
	public int insertMenu(int mmtid,String mname,float mprice,float mdiscount,Date mdate,int mstatus) {
		String sql="insert into Menu values(?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql, new Object[] {mmtid,mname,mprice,mdiscount,mdate,mstatus});
	}
}	
