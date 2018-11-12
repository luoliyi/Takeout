package com.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.dao.Orderdao;

public class Orderbo {

	Orderdao odao=new Orderdao();
	
	public int insert(String oNumber, Date oorderDate, BigDecimal omoney, boolean oinvoice, int ovid
			, int osid, String oremark, int ostatus){
		return odao.insert(oNumber, oorderDate, omoney, oinvoice, ovid, osid, oremark, ostatus);
	}
	
	public int update(int id, String oNumber, Date oorderDate, double omoney, int oinvoice, int ovid
			, int osid, String oremark, int ostatus){
		return odao.update(oNumber, oorderDate, omoney, oinvoice, ovid, osid, oremark, ostatus);
	}
	public int delete(String oNumber){
		return odao.delete(oNumber);
	}
	
}
