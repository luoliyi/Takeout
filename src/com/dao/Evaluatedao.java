package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.Jdbc;
import com.vo.Evaluatevo;

import text.DBUtil;

public class Evaluatedao {

	// Ìí
	public int insert(String eoNumber, double eminute, String eremark) {
		String sql = "INSERT INTO [evaluate] ([eoNumber] ,[eminute] " 
	+ ",[eremark]) VALUES (? ,? ,?)";
		return Jdbc.update(sql, eoNumber, eminute, eremark);
	}

	// ÐÞ
	public int update(int id, String eoNumber, double eminute, String eremark) {
		String sql = "UPDATE [evaluate] SET [eoNumber] =? ,[eminute] =? " 
	+ ",[eremark] =? WHERE eid=?";
		return Jdbc.update(sql, eoNumber, eminute, eremark, id);
	}

	// É¾
	public int delete(int id) {
		String sql = "delete from evaluate where eid=?";
		return Jdbc.update(sql, id);
	}

	public List<Evaluatevo> selectAll() {
		String sql = "select * from evaluate";
		return Jdbc.queryForList(sql, Evaluatevo.class);
	}
	public List<Evaluatevo> oneman(String Number){
		List<Evaluatevo> list=new ArrayList<Evaluatevo>();
		String sql="\r\n" + 
				"select eid,eoNumber,eminute,eremark from "
				+ "Evaluate E inner join Orders O ON "
				+ "E.eoNumber=O.oNumber inner join Vip V ON o.ovid=V.vid "
				+ "where V.vaccountNumber='"+Number+"'";
		ResultSet rs= DBUtil.executeQuery(sql);
		Evaluatevo E=null;
		try {
			while(rs.next()) {
				E=new Evaluatevo();
				E.setEid(rs.getInt(1));
				E.setEoNumber(rs.getString(2));
				E.setEminute(rs.getFloat(3));
				E.setEremark(rs.getString(4));
				list.add(E);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
