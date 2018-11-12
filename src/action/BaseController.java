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
			 //在当前Servlet实例中根据action找到方法信息
			try {
				Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class,
				        HttpServletResponse.class);
				 if (method != null) {
	                    // 在当前实例上调用方法method，指定参数request,response
						try {
							method.invoke(this, request, response);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							System.out.println("安全权限异常，一般来说，是由于java在反射时调用了private方法所导致的。如"
									+ "果是这种情况的话，要把反射pirvate的方向设置成public，再调用");
							//e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							System.out.println("此异常表明向方法传递了一个不合法或不正确的参数");
							//e.printStackTrace(); 
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							System.out.println("是一种包装由调用方法或构造方法所抛出异常的受查异常");
							e.printStackTrace();
						}
	                }
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
                response.getWriter().write("您请求的action不存在");
				//e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
                System.out.println("是安全异常的意思。你再仔细检查和安全设置相关的选项是否都设置正确。");
				//e.printStackTrace();
			}
			
		}else {
			response.getWriter().write("请指定参数action。");
		}
	}

}
