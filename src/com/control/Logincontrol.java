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
     
	//注册
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Loginbo bo = new Loginbo();
		Responsevo rvo = bo.login(user, password);
		preserve(rvo,request);
		return rvo.serialize();
	}
	//登录
	public String log_in(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Loginbo bo = new Loginbo();
		Responsevo rvo = bo.log_in(user, password);
		preserve(rvo,request);
		return rvo.serialize();
	}
	
	//对用户数据保存
	public void preserve(Responsevo rvo,HttpServletRequest request) {
		request.getSession().setAttribute("Vipvo", rvo.getData());
	}
	
	//再次确定登录
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
			case 2://修改登录密码
				return bo.updatepwo(id, value).serialize();
			case 3://修改手机号
				return bo.updateNumber(id, value).serialize();
			case 4://修改支付密码
				return bo.updatevpaymentPassword(id, value).serialize();
			}
			return new Responsevo(0).serialize();
		}
		return new Responsevo(1).serialize();
	}
	
	
	
}
