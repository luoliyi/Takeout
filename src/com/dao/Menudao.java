package com.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.util.Jdbc;
import com.vo.Menuvo;

public class Menudao {

	// ��
	public int insert(int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		String sql = "INSERT INTO [Menu] (mmtid,mname,mprice,mdiscount,mdate,mstatus) VALUES (?,?,?,?,?,?)";
		return Jdbc.update(sql, mmtid, mname, mprice, mdiscount, mdate, mstatus);
	}

	// ��
	public int update(int id, int mmtid, String mname, BigDecimal mprice, BigDecimal mdiscount, Date mdate,
			boolean mstatus) {
		String sql = "UPDATE [Menu] SET mmtid=?,mname=?,mprice=?,mdiscount=?,mdate=?,mstatus=? WHERE mid=?";
		return Jdbc.update(sql, mmtid, mname, mprice, mdiscount, mdate, mstatus, id);
	}

	// ɾ
	public int delete(int id) {
		String sql = "delete from Menu where mid=?";
		return Jdbc.update(sql, id);
	}

	//��ȡ��Ʒ
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
	
	//����ת����
	public Object[] turnArray(List<Integer> list) {
		Object[] result = new Object[list.size()];
		for (int i=0; i<list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	
	//��ǰ���һ�����ϼܲ�Ʒ
	public List<Menuvo> selectNewMenu(){
		java.sql.Timestamp datetime = new java.sql.Timestamp(recentlyDate().getTime());
		String sql = "select * from Menu where mdate > ?";
		return Jdbc.queryForList(sql, Menuvo.class, datetime);
	}
	
	//��ȡ��һ���µ�ʱ��
	private Date recentlyDate() {
		Calendar ca = Calendar.getInstance();//�õ�һ��Calendar��ʵ�� 
		ca.setTime(new Date()); //����ʱ��Ϊ��ǰʱ�� 
		ca.add(Calendar.MONTH, -1); //�·ݼ�1 
		return ca.getTime(); //���
	}
}
