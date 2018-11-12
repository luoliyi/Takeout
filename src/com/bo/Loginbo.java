package com.bo;

import com.dao.Vipdao;
import com.vo.Responsevo;
import com.vo.Vipvo;

public class Loginbo {

	// ע��
	public Responsevo login(String vaccountNumber, String vpassword) {
		Vipdao dao = new Vipdao();
		if(dao.selectNumber(vaccountNumber)==null) {
			if (dao.insert(vaccountNumber, vpassword, 1) > 0) {
				return new Responsevo(0,"ע��ɹ�!",dao.selectNumber(vaccountNumber));
			} else {
				return new Responsevo(1,"ע��ʧ��!", null);
			}
		} else {
			return new  Responsevo(1,"���Ѿ�ע�ᣡ", null);
		}
	}
	
	//��½
	public Responsevo log_in(String vaccountNumber, String vpassword) {
		Vipdao dao = new Vipdao();
		Vipvo vo = dao.selectNumber(vaccountNumber);
		if(vo!=null) {
			if(vo.getVpassword().equals(vpassword)) {
				return new Responsevo(0,"��¼�ɹ���", vo);
			}
		}
		return new Responsevo(1,"�û��������������!", vo);
	}	
	
	//��¼�����޸�
	public Responsevo updatepwo(int id, String pwo) {
		Vipdao dao = new Vipdao();
		if(dao.updatePwo(id, pwo)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//�����ֻ���
	public Responsevo updateNumber(int id, String vaccountNumber) {
		Vipdao dao = new Vipdao();
		if(dao.updateNumber(id, vaccountNumber)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//�޸�֧������
	public Responsevo updatevpaymentPassword(int id, String vpaymentPassword) {
		Vipdao dao = new Vipdao();
		if(dao.updatevpaymentPassword(id, vpaymentPassword)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
}
