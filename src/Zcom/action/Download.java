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
	        
	        
	        //���Ҫ���ص��ļ�·��
	        String path=getServletContext().getRealPath(filename);
	        System.out.println(path);
	        //����ļ���
	        //H:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\AjaxResult\images\12.jpg
	        String name=path.substring(path.lastIndexOf("\\")+1);
	        //ת��
	        name=URLEncoder.encode(name,"utf-8"); 
	        
	        //�޸�httpͷ�����������Ϊ����
	        response.setHeader("Content-Disposition", "attachment;filename="+name);
	        
	        //������������ļ����ֽ��� 
	        InputStream is=new FileInputStream(path);
	        byte[] bytes=new byte[is.available()];
	        is.read(bytes);
	        
	        //���ֽ���д��response��
	        response.getOutputStream().write(bytes);
	        is.close();
	        response.flushBuffer();
	        response.getOutputStream().flush();
	}

}
