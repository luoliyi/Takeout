package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.Jdbc;
import com.vo.MenuTypevo;

public class MenuTypedao {

	// 添
	public int insert(String mtname, int mtParentClass) {
		String sql = "insert into menuType(mtname,mtdate,mtParentClass) values(?,getdate(),?)";
		return Jdbc.update(sql, mtname, mtParentClass);
	}

	// 修
	public int update(int id, String mtname, int mtParentClass, boolean mtstatus) {
		String sql = "UPDATE [menuType] SET [mtname] =?,mtParentClass=?,mtstatus=? WHERE where mtid=?";
		return Jdbc.update(sql, mtname, mtParentClass, mtstatus, id);
	}

	// 删
	public int delete(int id) {
		String sql = "delete from [menuType] where mtid=?";
		return Jdbc.update(sql, id);
	}
	
	//查
	public List<MenuTypevo> selectAll(int mtParentClass) {
		String sql = "select mtid,mtname from (select mtid,mtname,mtstatus from MenuType "
				+ "where mtParentClass=?) menut where mtstatus = 0";
		return Jdbc.queryForList(sql, MenuTypevo.class, mtParentClass);
	}
	
	//菜品类型
	public List<MenuTypevo> menuType(int id){
		List<MenuTypevo> result = null;
		Connection con = null;
		try {
			String sql = "select mtid,mtname from MenuType where mtParentClass=?";
			con = Jdbc.getConnection();
			result = menuTypeSubclass(0,sql,con);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	//菜品类型子类
	public List<MenuTypevo> menuTypeSubclass(int id,String sql,Connection con){
		List<MenuTypevo> result = new ArrayList<MenuTypevo>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        MenuTypevo vo = null;
        try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new MenuTypevo(
						rs.getInt("mtid"),
						rs.getString("mtname")
						);
				result.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        for(int i=0; i<result.size(); i++) {
            if(result.get(i)!=null) {
            	List<MenuTypevo> list = 
            			menuTypeSubclass(result.get(i).getMtid(), sql, con);
                result.get(i).setSubclass(list);
            }
        } 
        
        return result;
	}
	
	//获取菜品类型与子类id 返回数组
	public List<Integer> menuid(int menuid){
		List<Integer> result = new ArrayList<Integer>();
		result.add(menuid);
		Connection con = Jdbc.getConnection();
		menuid(result, menuid, con);
		return result;
	}
	
	public void menuid(List<Integer> list, int menuid, Connection con){
		List<Integer> newList = new ArrayList<Integer>();
		String sql = "select mtid from menuType where mtParentClass = ?";
		PreparedStatement ps = null;
        ResultSet rs = null;
        boolean boo = true;
        try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuid);
			rs = ps.executeQuery();
			while(rs.next()) {
				newList.add(rs.getInt("mtid"));
				boo = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        if(!boo) {
        	for (Integer integer : newList) {
        		menuid(list,integer,con);
			}
        	collageList(list, newList);
        }
	}
	
	//向将参数二集合追加到参数集合一中
	public void collageList(List<Integer> listOne, List<Integer> listtwo){
		for (Integer integer : listtwo) {
			listOne.add(integer);
		}
		listtwo.clear();//清空集合
	}


}
