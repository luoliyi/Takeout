package Zcom.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Zcom.bos.Menuboimpl;
import Zcom.vos.Menu;



@WebServlet("/DownloadXLS")
public class DownloadXLS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadXLS() {
        super();
    }
    /*protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	 String filename = request.getParameter("filename");
	        request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("application/vnd.ms-excel");
	        // 写入bom头
	        byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
	    
	        String name=URLEncoder.encode("月度收入报表.csv","utf-8"); 
	        
	        //修改http头部，设置输出为附件
	        response.setHeader("Content-Disposition", "attachment;filename="+name);
	        
	        String result="编号,类型,名称,价格,状态,折扣,日期\r\n";
	        Menuboimpl mi=new Menuboimpl();
	        int mmtid=Integer.parseInt(request.getParameter("mmtid"));
	        //int mmtid=1;
	        List<Menu> list=null;
	        if(mmtid==0) {
	        	list=mi.getMenuAllListAll();
	        }else {
	        	 list=mi.getMenuAllList(mmtid);
	        }
	        for(Menu item:list) {
	        	 result+=item.getMid()+","+item.getMmtid()+","+item.getMname()+","+item.getMprice()+","+
	        item.getStatus()+","+item.getMdiscount()+","+item.getMdate()+"\r\n";
	        }
	        for (int i = 1; i <=10; i++) {
	            result+="2018-06-"+i+","+(i*10)+"万\r\n";
	        }
	        result=new String(result.getBytes("utf-8"),"utf-8");
	        //将字节流写入response中
	        response.getOutputStream().write(uft8bom);  //写入头部解决乱码问题
	        response.getOutputStream().write(result.getBytes("utf-8"));
	        response.flushBuffer();
	        response.getOutputStream().flush();
    }*/
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String filename = request.getParameter("filename");
	        request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("application/vnd.ms-excel");
	        // 写入bom头
	        byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
	    
	        String name=URLEncoder.encode("菜品详细清单.csv","utf-8"); 
	        
	        //修改http头部，设置输出为附件
	        response.setHeader("Content-Disposition", "attachment;filename="+name);
	        
	        String result="编号,类型,名称,价格,状态,折扣,日期\r\n";
	        Menuboimpl mi=new Menuboimpl();
	        int mmtid=Integer.parseInt(request.getParameter("mmtid"));
	        //int mmtid=1;
	        List<Menu> list=null;
	        if(mmtid==0) {
	        	list=mi.getMenuAllListAll();
	        }else {
	        	 list=mi.getMenuAllList(mmtid);
	        }
	        for(Menu item:list) {
	        	 result+=item.getMid()+","+item.getMmtid()+","+item.getMname()+","+item.getMprice()+","+
	        item.getStatus()+","+item.getMdiscount()+","+item.getMdate()+"\r\n";
	        }
	       
	        result=new String(result.getBytes("utf-8"),"utf-8");
	        //将字节流写入response中
	        response.getOutputStream().write(uft8bom);  //写入头部解决乱码问题
	        response.getOutputStream().write(result.getBytes("utf-8"));
	        response.flushBuffer();
	        response.getOutputStream().flush();
	       
	}/*
	//根据条件进行查询
		public void selectMenu(HttpServletRequest request, HttpServletResponse response) {
			Menuboimpl mb=new Menuboimpl();
			List<Menu> list=new ArrayList<Menu>();
			int mmtid=Integer.parseInt(request.getParameter("mmtid"));
			List<Menu> item=mb.getMenuAllList(mmtid);
			for(Menu rs:item) {
				list.add(new Menu(rs.getMid(),rs.getMmtid(),rs.getMname(),rs.getMprice(),rs.getMdiscount()
						,rs.getMdate(),rs.getStatus())
						);
			}
			try {
				response.getWriter().print(JsonUtils.toJson(list));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

}
