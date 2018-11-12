package com.bo;

import com.dao.Vipdao;
import com.vo.Responsevo;
import com.vo.Vipvo;

public class Loginbo {

	// ×¢²á
	public Responsevo login(String vaccountNumber, String vpassword) {
		Vipdao dao = new Vipdao();
		if(dao.selectNumber(vaccountNumber)==null) {
			if (dao.insert(vaccountNumber, vpassword, 1) > 0) {
				return new Responsevo(0,"×¢²á³É¹¦!",dao.selectNumber(vaccountNumber));
			} else {
				return new Responsevo(1,"×¢²áÊ§°Ü!", null);
			}
		} else {
			return new  Responsevo(1,"ÄúÒÑ¾­×¢²á£¡", null);
		}
	}
	
	//µÇÂ½
	public Responsevo log_in(String vaccountNumber, String vpassword) {
		Vipdao dao = new Vipdao();
		Vipvo vo = dao.selectNumber(vaccountNumber);
		if(vo!=null) {
			if(vo.getVpassword().equals(vpassword)) {
				return new Responsevo(0,"µÇÂ¼³É¹¦£¡", vo);
			}
		}
		return new Responsevo(1,"ÓÃ»§»òÃÜÂëÊäÈë´íÎó!", vo);
	}	
	
	//µÇÂ¼ÃÜÂëĞŞ¸Ä
	public Responsevo updatepwo(int id, String pwo) {
		Vipdao dao = new Vipdao();
		if(dao.updatePwo(id, pwo)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//¸ü»»ÊÖ»úºÅ
	public Responsevo updateNumber(int id, String vaccountNumber) {
		Vipdao dao = new Vipdao();
		if(dao.updateNumber(id, vaccountNumber)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//ĞŞ¸ÄÖ§¸¶ÃÜÂë
	public Responsevo updatevpaymentPassword(int id, String vpaymentPassword) {
		Vipdao dao = new Vipdao();
		if(dao.updatevpaymentPassword(id, vpaymentPassword)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
}
