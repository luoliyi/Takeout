package com.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Loginbo;
import com.vo.Responsevo;
import com.vo.Vipvo;

/**
 * Servlet implementation class Logincontrol
 */
@WebServlet(urlPatterns= {"/Login.do"})
public class Logincontrol extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	//ע��
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Loginbo bo = new Loginbo();
		Responsevo rvo = bo.login(user, password);
		preserve(rvo,request);
		return rvo.serialize();
	}
	//��¼
	public String log_in(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Loginbo bo = new Loginbo();
		Responsevo rvo = bo.log_in(user, password);
		preserve(rvo,request);
		return rvo.serialize();
	}
	
	//���û����ݱ���
	public void preserve(Responsevo rvo,HttpServletRequest request) {
		request.getSession().setAttribute("Vipvo", rvo.getData());
	}
	
	//�ٴ�ȷ����¼
	public String Determinelogin(HttpServletRequest request
			, HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Vipvo vo = (Vipvo)request.getSession().getAttribute("Vipvo");
		String vaccountNumber = request.getParameter("vaccountNumber");
		String vpassword = request.getParameter("vpassword");
		if(vaccountNumber.equals(vo.getVaccountNumber()) && 
				vpassword.equals(vo.getVpassword())) {
			int determine = Integer.parseInt(request.getParameter("determine"));
			String value = request.getParameter("value");
			Loginbo bo = new Loginbo();
			switch (determine) {
			case 1:
				return new Responsevo(0).serialize();
			case 2://�޸ĵ�¼����
				return bo.updatepwo(id, value).serialize();
			case 3://�޸��ֻ���
				return bo.updateNumber(id, value).serialize();
			case 4://�޸�֧������
				return bo.updatevpaymentPassword(id, value).serialize();
			}
			return new Responsevo(0).serialize();
		}
		return new Responsevo(1).serialize();
	}
	
	
	
}
