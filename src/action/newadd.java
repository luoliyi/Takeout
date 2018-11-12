package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Evaluatedao;
import com.dao.Locationsdao;
import com.dao.Orderdao;
import com.dao.Vipdao;
import com.vo.UI;

import text.JSON;

/**
 * Servlet implementation class newadd
 */
@WebServlet("/newadd")
public class newadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Locationsdao dao=new Locationsdao();
	Vipdao V=new Vipdao();
    public newadd() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
		    response.setContentType("application/json;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		    String list=request.getParameter("list");
		    if(list.equals("list")) {
				String fu=JSON.JOSON(dao.selectAll("15070237082"));
				System.out.print(JSON.JOSON(dao.selectAll("15070237082")));
				response.getWriter().print(fu);
		    }
		    else if(list.equals("li")) {
		    	Evaluatedao E=new Evaluatedao();
		    	System.out.println(JSON.JOSON(E.oneman("13926901506")));
		    	response.getWriter().print(JSON.JOSON(E.oneman("13926901506")));//传入会员账号
		    }
		    else if(list.equals("menber")) {
		    	//data:{brtime:time,mname:name,madd:address,memal:emal,msex,sex,mlike,like,vaccountNumber:Number},
		    	
		    	String brtime=request.getParameter("brtime");
		    	Date da=null;
		    	
		    	try {
					da=new SimpleDateFormat("yyyy-MM-dd").parse(brtime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	java.sql.Timestamp datetime=new java.sql.Timestamp(da.getTime());
		    	String mname=request.getParameter("mname");
		    	String madd=request.getParameter("madd");
		    	String memal=request.getParameter("memal");
		    	String msex=request.getParameter("msex");
		    	String milike=request.getParameter("mlike");
		    	String vaccountNumber=request.getParameter("vaccountNumber");
		    	String vpersonality=request.getParameter("vpersonality");
		    	System.out.print(brtime+"\r"+mname+"\r"+madd+"\r"+memal+"\r"+msex+"\r"+milike+"\r"+vaccountNumber);
		    	// 姓名  性别   时间 地区 邮箱 口味爱好 个性  手机号码
		    	int i= V.update2(mname, msex, datetime, madd, memal, milike, vpersonality, "15070237082");
		    	if(i>0){
		    		response.getWriter().println("{\"msg\":\"保存成功\"}");
		    	}
		    	
		    }
		    else if(list.equals("point")){
		    	Vipdao V=new Vipdao();
		    	System.out.println("S");
		    	System.out.print(JSON.JOSON(V.selectNumber("15070237082")));
				String fu=JSON.JOSON(V.selectNumber("15070237082"));
				response.getWriter().print(fu);
		    }
		    else if(list.equals("RH")){
		    	//data:{JF:value,mname:MyJF},
		    	int JF=Integer.parseInt(request.getParameter("JF"));
		    	int MyJF=Integer.parseInt(request.getParameter("mname"));
		    	
		    	Vipdao V=new Vipdao();
		    	int i=V.updatevintegral(JF, MyJF, "15070237082");
		    	System.out.println(i);
		    	if(i>0){
		    		response.getWriter().println("{\"msg\":\"兑换成功\"}");
		    	}
		    	else{
		    		response.getWriter().println("{\"msg\":\"兑换失败\"}");
		    	}
		    }
		    else if(list.equals("lookvalue")) {
		    	 String sql="select * from Orders";
		    	 Orderdao or=new Orderdao();
		    	 UI li=new UI();
		    	 li.setCode(0);
		    	 li.setMsg("获得数据成功");
		    	 li.setCount(500);
		         li.setData(or.addOrders(1, 10,sql));
		         System.out.println(JSON.JOSON(li));
		         response.getWriter().print(JSON.JOSON(li));
		    }
		    else if(list.equals("Xorder")) {
		    	 String sql="select*from Orders where ostatus=0";
		    	 Orderdao or=new Orderdao();
		    	 UI li=new UI();
		    	 li.setCode(0);
		    	 li.setMsg("获得数据成功");
		    	 li.setCount(500);
		         li.setData(or.addOrders(1, 10,sql));
		         System.out.println(JSON.JOSON(li));
		         response.getWriter().print(JSON.JOSON(li));
		    }
		    else {
		    	//data:{username:name,usermob:userm,house:suggestId,usersex:sex}
		    	String username=request.getParameter("username");//联系人名称
		    	String usermob=request.getParameter("usermob");//联系人电话
		    	String house=request.getParameter("house");//联系人地址
		    	String usersex=request.getParameter("usersex");//联系人性别
		    	System.out.println(username+"\r"+usermob+"\r"+house+"\r"+usersex);
		    	//String pid   账号
		    	int vid=dao.vidnuber("15070237082");//通过账号获取VID
		    	System.out.println(vid);
				if(vid>0){
			    		dao.insert(vid,username,usersex,usermob,house);
			    		response.getWriter().println("{\"msg\":\"添加成功\"}");
				}
				else{
					//返回一个失败的回去
				}
		    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
