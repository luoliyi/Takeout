package action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BaseController")
public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BaseController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String action=request.getParameter("action");
		if(action!=null) {
			 //�ڵ�ǰServletʵ���и���action�ҵ�������Ϣ
			try {
				Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class,
				        HttpServletResponse.class);
				 if (method != null) {
	                    // �ڵ�ǰʵ���ϵ��÷���method��ָ������request,response
						try {
							method.invoke(this, request, response);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							System.out.println("��ȫȨ���쳣��һ����˵��������java�ڷ���ʱ������private���������µġ���"
									+ "������������Ļ���Ҫ�ѷ���pirvate�ķ������ó�public���ٵ���");
							//e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							System.out.println("���쳣�����򷽷�������һ�����Ϸ�����ȷ�Ĳ���");
							//e.printStackTrace(); 
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							System.out.println("��һ�ְ�װ�ɵ��÷������췽�����׳��쳣���ܲ��쳣");
							e.printStackTrace();
						}
	                }
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
                response.getWriter().write("�������action������");
				//e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
                System.out.println("�ǰ�ȫ�쳣����˼��������ϸ���Ͱ�ȫ������ص�ѡ���Ƿ�������ȷ��");
				//e.printStackTrace();
			}
			
		}else {
			response.getWriter().write("��ָ������action��");
		}
	}

}
