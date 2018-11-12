package com.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.util.Jdbc;
import com.vo.Menuvo;

public class Menudao {

	// 添
	public int insert(int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		String sql = "INSERT INTO [Menu] (mmtid,mname,mprice,mdiscount,mdate,mstatus) VALUES (?,?,?,?,?,?)";
		return Jdbc.update(sql, mmtid, mname, mprice, mdiscount, mdate, mstatus);
	}

	// 修
	public int update(int id, int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		String sql = "UPDATE [Menu] SET mmtid=?,mname=?,mprice=?,mdiscount=?,mdate=?,mstatus=? WHERE mid=?";
		return Jdbc.update(sql, mmtid, mname, mprice, mdiscount, mdate, mstatus, id);
	}

	// 删
	public int delete(int id) {
		String sql = "delete from Menu where mid=?";
		return Jdbc.update(sql, id);
	}

	//获取菜品
	public List<Menuvo> selectTypeMenu(List<Integer> list) {
		String sql = "select mid,mmtid,mname,mprice,mdiscount,mdate,mstatus"
				+ " from Menu where mmtid in (";
		String delimiter = "";
		for(int i=0; i<list.size(); i++) {
			sql +=delimiter+"?";
			delimiter = ",";
		}
		sql +=")";
		Object[] array = turnArray(list);
		return Jdbc.queryForList(sql, Menuvo.class, array);
	}
	
	public List<Menuvo> selectTypeMenu(int id) {
		String sql = "select mid,mmtid,mname,mprice,mdiscount,mdate,mstatus from Menu where mid=?";
		return Jdbc.queryForList(sql, Menuvo.class, id);
	}
	
	//集合转数组
	public Object[] turnArray(List<Integer> list) {
		Object[] result = new Object[list.size()];
		for (int i=0; i<list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	
	//当前最近一个月上架菜品
	public List<Menuvo> selectNewMenu(){
		java.sql.Timestamp datetime = new java.sql.Timestamp(recentlyDate().getTime());
		String sql = "select * from Menu where mdate > ?";
		return Jdbc.queryForList(sql, Menuvo.class, datetime);
	}
	
	//获取上一个月的时间
	private Date recentlyDate() {
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		ca.setTime(new Date()); //设置时间为当前时间 
		ca.add(Calendar.MONTH, -1); //月份减1 
		return ca.getTime(); //结果
	}
}
