package com.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.util.Jdbc;
import com.vo.Vipvo;

import util.DBUtil;

public class Vipdao {

	// ע��
	public int insert(String vaccountNumber, String vpassword, int vvtid) {
		String sql = "insert into Vip(vaccountNumber,vpassword,vvtid) values(?,?,?)";
		return Jdbc.update(sql, vaccountNumber, vpassword, vvtid);
	}

	// ��������
	public int insert(String vname, String vsex, String vheadPortrait, Date vbirthday, String vregion, String vemail,
			String vflavor, String vpersonality) {
		String sql = "insert into vip([vname],[vsex],[vheadPortrait]"
				+ ",[vbirthday],[vregion],[vemail],[vflavor],[vpersonality])";
		return Jdbc.update(sql, vname, vsex, vheadPortrait, vbirthday, vregion, vemail, vflavor, vpersonality);
	}

	// ��
	public int update(int id, String vname, String vsex, String vheadPortrait, Date vbirthday, String vregion,
			String vemail, String vflavor, String vpersonality) {
		String sql = "UPDATE [dbo].[vip] SET [vname] = ?,[vsex] = ?,[vheadPortrait] = ?"
				+ ",[vbirthday] = ?,[vregion] = ?,[vemail] = ?,[vflavor] = ?" + ",[vpersonality] = ? WHERE vid=?";
		return Jdbc.update(sql, vname, vsex, vheadPortrait, vbirthday, vregion, vemail, vflavor, vpersonality, id);
	}
	
	
	//��Ա�޸�����
	public int updatePwo(int id, String pwo) {
		String sql ="update Vip set vpassword=? where vid=?";
		return Jdbc.update(sql, pwo,id);
	}

	//��Ա�޸�֧������
	public int updateNumber(int id, String vaccountNumber) {
		String sql ="update Vip set vaccountNumber=? where vid=?";
		return Jdbc.update(sql, vaccountNumber,id);
	}
	
	//�޸�֧������
	public int updatevpaymentPassword(int id, String vpaymentPassword) {
		String sql ="update Vip set vpaymentPassword=? where vid=?";
		return Jdbc.update(sql, vpaymentPassword,id);
	}
	
	// ɾ
	public int delete(int id) {
		String sql = "delete from vip where vid=?";
		return Jdbc.update(sql, id);
	}

	/**
	 * �˺Ų�ѯ
	 * @param accountNumber
	 * @return Vipvo
	 */
	public Vipvo selectNumber(String accountNumber) {
		String sql = "select * from Vip where vaccountNumber=?";
		return Jdbc.queryForObject(sql, Vipvo.class,accountNumber);
	}
	
	public Vipvo selectid(int id) {
		String sql="select * from Vip where vid=?";
		return Jdbc.queryForObject(sql, Vipvo.class,id);
	}

	public int update2(String mname, String msex, Timestamp datetime, String madd, String memal, String milike,
			String vpersonality, String Number) {
		String sql = "update Vip set vname='"+mname+"',"
				+ "vsex='��',vbirthday='"+datetime+"',vregion='"+madd+"',"
				+ "vemail='"+memal+"',vflavor='"+milike+"',vpersonality='"+vpersonality+"' "
				+ "where vaccountNumber='"+Number+"'";
		return DBUtil.executeUpdate(sql);
	}

	public int updatevintegral(int jF, int myJF, String Number) {
		int i=DBUtil.executeUpdate("update Vip set vintegral="+myJF+"-"+jF+" where vaccountNumber='"+Number+"'");
		return i;
	}
}
