package com.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Huiyuanmainbo;

/**
 * Servlet implementation class Huiyuanmain
 */
@WebServlet("/Huiyuanmaincontrol")
public class Huiyuanmaincontrol extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Huiyuanmaincontrol() {
        super();
    }
    
    Huiyuanmainbo hybo=new Huiyuanmainbo();
    public String selectOneVip(HttpServletRequest request, HttpServletResponse response){
    	
    	String vipno=request.getParameter("vipno");
    	return hybo.selectOne(vipno).serialize();
    }
    
}
