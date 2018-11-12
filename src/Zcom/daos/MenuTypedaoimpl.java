package Zcom.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Zcom.vos.MenuType;

import util.DBUtil;

public class MenuTypedaoimpl {
	public List<MenuType> getType(int mtParentClass){
		String sql="select *from MenuType where mtParentClass=?";
		ResultSet rs=(ResultSet)DBUtil.executeQuery(sql, new Object[] {mtParentClass});
		List<MenuType> list=new ArrayList<MenuType>();
		MenuType model=null;
		try {
			while(rs.next()) {
				model=new MenuType();
				model.setMtid(rs.getInt(1));
				model.setMtname(rs.getString(2));
				model.setMtParentClass(rs.getInt(3));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<MenuType> getTypes(String mtname){
		String sql="select *from MenuType where mtname=?";
		ResultSet rs=(ResultSet)DBUtil.executeQuery(sql, new Object[] {mtname});
		List<MenuType> list=new ArrayList<MenuType>();
		MenuType model=null;
		try {
			while(rs.next()) {
				model=new MenuType();
				model.setMtid(rs.getInt(1));
				model.setMtname(rs.getString(2));
				model.setMtParentClass(rs.getInt(3));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}