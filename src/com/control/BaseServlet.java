package com.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		try {
			if(action != null) {
				Method method = this.getClass().getDeclaredMethod(action,
						HttpServletRequest.class,
						HttpServletResponse.class);
				method.setAccessible(true);
				try {
					System.out.println(action);
					String data = (String) method.invoke(this, request, response);
					response.getWriter().write(data);
				} catch (IllegalAccessException e) {
					response.getWriter().write("您的动作不存在!1");
				} catch (IllegalArgumentException e) {
					response.getWriter().write("您的动作不存在!2");
				} catch (InvocationTargetException e) {
					response.getWriter().write("您的动作不存在!3");
				}
			}
		} catch (NoSuchMethodException e) {
			response.getWriter().write("您的动作不存在!4");
		} catch (SecurityException e) {
			response.getWriter().write("您的动作不存在!5");
		}
	}

}
