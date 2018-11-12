package com.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.util.Jdbc;
import com.vo.Ordersvo;

import text.DBUtil;

public class Orderdao {

	//添
	public int insert(String oNumber, Date oorderDate, BigDecimal omoney, 
			boolean oinvoice, int ovid, int osid, String oremark, int ostatus) {
		String sql ="INSERT INTO Orders(oNumber,oorderDate,omoney,oinvoice,ovid" + 
				",osid,oremark,ostatus) VALUES (?,?,?,?,?,?,?,?)";
		return Jdbc.update(sql, oNumber, oorderDate, omoney, oinvoice
				, ovid, osid, oremark, ostatus);
	}
	
	//修
	public int update(String oNumber, Date oorderDate, double omoney, int oinvoice, int ovid
			, int osid, String oremark, int ostatus) {
		String sql = "UPDATE Orders set oorderDate=?,omoney=?,oinvoice=?,ovid=?" + 
				",osid=?,oremark=?,ostatus=? WHERE oNumber=?";
		return Jdbc.update(sql, oorderDate, omoney, ovid, osid, oremark, ostatus, oNumber);
	}
	
	//修改订单
	public int update(String oNumber, int ostatus) {
		String sql = "UPDATE Orders set ostatus=? WHERE oNumber=?";
		return Jdbc.update(sql, ostatus, oNumber);
	}
	
	//删
	public int delete(String oNumber) {
		String sql ="delete from [Orders] where oNumber=?";
		return Jdbc.update(sql, oNumber);
	}
	
	//查询订单
	public Ordersvo selectOrders(String oNumber) {
		String sql ="select oorderDate, oNumber,omoney,oinvoice,"
				+ "ovid,osid,oremark,ostatus from Orders where oNumber=?";
		return Jdbc.queryForObject(sql, Ordersvo.class, oNumber);
	}
	
	/**查询会员所有订单*/
	public List<Ordersvo> selectAllVipOrders(int id){
		String sql ="select * from Orders "
				+ "where ovid=? ORDER BY oorderDate DESC";
		//方法1
	/*	return Jdbc.queryForList(sql, Ordersvo.class, id);*/
		
		//方法2
		List<Ordersvo> oders=new ArrayList<>();
		ResultSet rs=DBUtil.executeQuery(sql,new Object[]{id});
		Ordersvo or=null;
		try {
			while(rs.next()){
				or=new Ordersvo();
				or.setoNumber(rs.getString(1));
				or.setOorderDate(rs.getTimestamp(2));
				or.setOmoney(rs.getBigDecimal(3));
				or.setOinvoice(rs.getBoolean(4));
				or.setOvid(rs.getInt(5));
				or.setOsid(rs.getInt(6));
				or.setOremark(rs.getString(7));
				or.setOstatus(rs.getInt(8));
				oders.add(or);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oders;
		
	}
	
	public List<Ordersvo> selectVipOrders(int id, int ostatus){
		String sql ="select oNumber,oorderDate,omoney,oinvoice,ovid,osid,oremark,ostatus from Orders "
				+ "where ovid=? and ostatus=? ORDER BY oorderDate DESC";
		return Jdbc.queryForList(sql, Ordersvo.class, id);
	}
	
	public List<Ordersvo> addOrders(int page,int li,String sql){
		List<Ordersvo> oder=new ArrayList<>();
		ResultSet rs=DBUtil.executeQuery(sql);
		Ordersvo or=null;
		try {
			while(rs.next()) {
				or=new Ordersvo();
				or.setoNumber(rs.getString(1));
				or.setOorderDate(rs.getDate(2));
				or.setOmoney(rs.getBigDecimal(3));
				or.setOinvoice(rs.getBoolean(4));
				or.setOvid(rs.getInt(5));
				or.setOsid(rs.getInt(6));
				or.setOremark(rs.getString(7));
				or.setOstatus(rs.getInt(8));
				
				oder.add(or);
				System.out.println("---------------------------------");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Ordersvo> list=new ArrayList<Ordersvo>();
		int start=(page-1)*li;
	        for (int i =start; i <start+li&&i<oder.size(); i++) {
	            list.add(oder.get(i));
	        }
		return list;
	}
	
}
