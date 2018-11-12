package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.Jdbc;
import com.vo.Consumptionvo;

public class Consumptiondao {

	// 添
	public int insert(String oNumber, int cmid, int cquantity) {
		String sql = "INSERT INTO [dbo].[consumption] ([coNumber] ,[cmid] " 
				+ ",[cquantity] ) VALUES (? ,? ,? )";
		return Jdbc.update(sql, oNumber, cmid, cquantity);
	}

	// 修
	public int update(int id, String oNumber, int cmid, int cquantity) {
		String sql = "UPDATE [dbo].[consumption] SET [coNumber] =? ,[cmid] =? " 
				+ ",[cquantity] =? WHERE cid=?";
		return Jdbc.update(sql, oNumber, cmid, cquantity, id);
	}

	// 删
	public int delete(int id) {
		String sql = "delete from consumption where cid=?";
		return Jdbc.update(sql, id);
	}
	
	public int delete(String coNumber) {
		String sql = "delete from consumption where coNumber=?";
		return Jdbc.update(sql, coNumber);
	}

	public List<Consumptionvo> selectAll() {
		String sql = "select * from consumption";
		return Jdbc.queryForList(sql, Consumptionvo.class);
	}

	// 查询菜品销售数量
	public int menuCount(int menuid) {
		String sql = "Select sum(cquantity) from Consumption where cmid = ?";
		int result = 0;
		Connection con = Jdbc.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, menuid);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	//查询订单消费
	public List<Consumptionvo> selectOrders(String coNumber){
		String sql = "select cid,mname as coNumber,cquantity from Consumption left join (select mid,mname from Menu) as m "
				+ "on m.mid=Consumption.cmid where coNumber=?";
		return Jdbc.queryForList(sql, Consumptionvo.class, coNumber);
	}
	
}
