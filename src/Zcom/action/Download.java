package Zcom.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Download() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String filename = request.getParameter("filename");
	        request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        
	        
	        //获得要下载的文件路径
	        String path=getServletContext().getRealPath(filename);
	        System.out.println(path);
	        //获得文件名
	        //H:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\AjaxResult\images\12.jpg
	        String name=path.substring(path.lastIndexOf("\\")+1);
	        //转码
	        name=URLEncoder.encode(name,"utf-8"); 
	        
	        //修改http头部，设置输出为附件
	        response.setHeader("Content-Disposition", "attachment;filename="+name);
	        
	        //输入流，获得文件的字节流 
	        InputStream is=new FileInputStream(path);
	        byte[] bytes=new byte[is.available()];
	        is.read(bytes);
	        
	        //将字节流写入response中
	        response.getOutputStream().write(bytes);
	        is.close();
	        response.flushBuffer();
	        response.getOutputStream().flush();
	}

}
