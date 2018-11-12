package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.Jdbc;
import com.vo.ShoppingCartvo;

public class ShoppingCartdao {
	// 添
	public int insert(int scvip, int scmenu) {
		String sql = "insert into ShoppingCart(scvip,scmenu) VALUES(?,?)";
		return Jdbc.update(sql, scvip, scmenu);
	}
	//修改
	public int update(int id, int scvip, int scmenu, int scquantity) {
		String sql = "update ShoppingCart set scvip=?, scmenu=?, scquantity=? where scid = ?";
		return Jdbc.update(sql, scvip , scmenu, scquantity, id);
	}

	// 修 追加数量
	public int append(int id, int scquantity) {
		String sql = "UPDATE ShoppingCart SET scquantity +=? WHERE scid=?";
		return Jdbc.update(sql, scquantity, id);
	}
	
	//删除
	public int delete(int id) {
		String sql = "delete from ShoppingCart where scid=?";
		return Jdbc.update(sql, id);
	}
	//删除会员购物车
	public int deleteVip(int vipid) {
		String sql = "delete from ShoppingCart where scvip=?";
		return Jdbc.update(sql, vipid);
	}
	//查询菜品是否以点
	public ShoppingCartvo selectMenu(int id, int menuid) {
		String sql = "select scid,scquantity from (select scid,scmenu,scquantity from ShoppingCart "
				+ "where scvip=?) as shopp where scmenu=?";
		Connection con = Jdbc.getConnection();
		PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartvo result = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, menuid);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = new ShoppingCartvo(
						rs.getInt("scid"),
						rs.getInt("scquantity")
						);
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
	

	//查询会员购物车菜品
	public List<ShoppingCartvo> selectVipMenu(int id) {
		String sql = "select scid,scmenu,mname,mprice,scquantity from ShoppingCart " + 
				"left join Menu on scmenu=mid" + 
				" where scvip=?";
		return Jdbc.queryForList(sql, ShoppingCartvo.class, id);
	}
	
	//查询所有
	public List<ShoppingCartvo> selectAll() {
		String sql = "select scid,scvip,scmenu,scquantity from ShoppingCart ";
		return Jdbc.queryForList(sql, ShoppingCartvo.class );
	}
}
