package com.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Initialisebo;

/**
 * Servlet implementation class Initialisecontrol
 */
@WebServlet(urlPatterns= {"/Initialisecontrol.do"})
public class Initialisecontrol extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//菜品菜单
	public String menuTypeMenu(HttpServletRequest request, HttpServletResponse response) {
		Initialisebo bo = new Initialisebo();
			return bo.typeMenu().serialize();
	}
	
	//菜品
	public String typeMenu(HttpServletRequest request, HttpServletResponse response) {
		Initialisebo bo = new Initialisebo();
		int menuTypeid = Integer.parseInt(request.getParameter("menuTypeid"));
		return bo.menu(menuTypeid).serialize();
	}
	
	//新菜品
	public String selectNewMenu(HttpServletRequest request, HttpServletResponse response) {
		Initialisebo bo = new Initialisebo();
		return bo.selectNewMenu().serialize();
	}
}
