package com.bo;

import java.util.Date;

import com.dao.Vipdao;
import com.vo.Vipvo;

public class Huiyuanmainbo {

	Vipdao vipdao=new 	Vipdao();
	
	
	public Vipvo selectOne(String vaccountNumber){
		return vipdao.selectNumber(vaccountNumber);
	}
	
	public int update(int id, String vname, String vsex, String vheadPortrait, Date vbirthday, String vregion,
			String vemail, String vflavor, String vpersonality){
		return vipdao.update(id, vname, vsex, vheadPortrait, vbirthday, vregion, vemail, vflavor, vpersonality);
	}
	
	public int delete(int id){
		return vipdao.delete(id);
	}
	
	public  int insert(String vname, String vsex, String vheadPortrait, Date vbirthday, String vregion, String vemail,
			String vflavor, String vpersonality){
		return vipdao.insert(vname, vsex, vheadPortrait, vbirthday, vregion, vemail, vflavor, vpersonality);
	}
	
	public int regsist(String vaccountNumber, String vpassword, int vvtid){
		return vipdao.insert(vaccountNumber, vpassword, vvtid);
	}
}
