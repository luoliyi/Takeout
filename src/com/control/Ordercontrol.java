package com.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Orderbo;

/**
 * Servlet implementation class Ordercontrol
 */
@WebServlet("/Ordercontrol")
public class Ordercontrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ordercontrol() {
        super();
        // TODO Auto-generated constructor stub
    }

    Orderbo ordbo=new Orderbo();
    
    

}
