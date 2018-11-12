package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.Jdbc;
import com.vo.Locationsvo;
import com.vo.Vipvo;

import text.DBUtil;

public class Locationsdao {

	// ��
	public int insert(int svid, String sname, String lsex, String sphone, String saddress) {
		String sql = "insert into Locations(lvid,lname,lsex,lphone,laddress) values(?,?,?,?,?)";
		return Jdbc.update(sql, svid, sname, lsex, sphone, saddress);
	}

	// ��
	public int update(int id, String sname, String ssex, String sphone, String saddress) {
		String sql = "update Locations set lname=?,lsex=?,lphone=?,laddress=? where lid=?";
		return Jdbc.update(sql, sname, ssex, sphone, saddress, id);
	}

	// ɾ
	public int delete(int id) {
		String sql = "delete from Locations where lid=?";
		return Jdbc.update(sql, id);
	}

	//��ѯ��������  �����˺Ų�ѯ���˺���ص��ջ���ַ
	public List<Locationsvo> selectAll(String Number) {
		String sql = "select lid,lvid,lname,lsex,lphone,laddress,lstatus from Locations L "
				+ "inner join Vip V On L.lvid=V.vid "
				+ "where V.vaccountNumber='"+Number+"'";
		return Jdbc.queryForList(sql, Locationsvo.class);
	}
	
	//��ѯ��Ա��ַ
	public List<Locationsvo> selectVip(int vipid) {
		String sql = "select * from Locations where lvid=?";
		return Jdbc.queryForList(sql, Locationsvo.class, vipid);
	}
	
	//��ѯ��ԱĬ�ϵ�ַ
	public Locationsvo selectDefault(int vipid) {
		String sql = "select * from Locations where lvid=? and lstatus=1";
		return Jdbc.queryForObject(sql, Locationsvo.class, vipid);
	}
	public int vidnuber(String name) {
		String sql = "select * from Vip where vaccountNumber='"+name+"\'";
		ResultSet rs= DBUtil.executeQuery(sql);
		int vid=0;
		try {
			while(rs.next()) {
				vid=rs.getInt("vid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vid;
	}
	
}
