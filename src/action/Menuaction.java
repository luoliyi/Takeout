package action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Zcom.bos.MenuTypeboimpl;
import Zcom.bos.Menuboimpl;
import Zcom.vos.Menu;
import Zcom.vos.MenuType;
import Zcom.vos.Menus;
import Zcom.vos.R;

import jsonUtil.JsonUtils;
import util.DBUtil;

/**
 * Servlet implementation class Menuaction
 */
@WebServlet("/Menuaction")
public class Menuaction extends BaseController {
	private static final long serialVersionUID = 1L;
	private static Menuboimpl mb=new Menuboimpl();
	//分页查询
	public void lookvalue(HttpServletRequest request, HttpServletResponse response) {
		int page=Integer.parseInt(request.getParameter("page"));//当前页数
		int limit=Integer.parseInt(request.getParameter("limit"));//总数
		String procName="pro_select";
		String procNames="selectMenuType";
		Object[] in= {page,limit};
		ResultSet rs=(ResultSet)DBUtil.executeProcedure(procName, in);
		List<Menu> item=mb.getMenuAllListAll();
		int i=0;
		for(Menu items:item) {
			i++;
		}
		try {
			List<Menus> list2=new ArrayList<Menus>();
			while(rs.next()) {
				//获得类型
				Object[] ins= {rs.getInt(8)};
				String s2="";
				ResultSet rss=(ResultSet)DBUtil.executeProcedure(procNames, ins);
				while(rss.next()) {
					s2=rss.getString(1);
				}
				String s="";
				if(rs.getInt(7)==1) {
					s="下架";
				}else {
					s="有余";
				}
				list2.add(new Menus(rs.getInt(1),s2,rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getDate(6),s));
			}
			try {
				R r=new R();
				r.setCode(0);
				r.setCount(i);
				r.setMsg("获得数据成功！");
				r.setData(list2);
				response.getWriter().print(JsonUtils.toJson(r));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//查询所有菜名
	public void selectAllMenu(HttpServletRequest request, HttpServletResponse response) {
		List<Menu> list=new ArrayList<Menu>();
		List<Menu> item=mb.getMenuAllListAll();
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
	}
	//根据条件进行查询
	public void selectMenu(HttpServletRequest request, HttpServletResponse response) {
		List<Menu> list=new ArrayList<Menu>();
		int mmtid=1;
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
	}
	//删除
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) {
		int mid=Integer.parseInt(request.getParameter("mid"));
		if(mb.deleteMenu(mid)>0) {
			try {
				response.getWriter().print("{\"msg\":\"删除成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//修改
	public void updateMenu(HttpServletRequest request, HttpServletResponse response) {
		String mmtid=request.getParameter("mmtid");
		MenuTypeboimpl mt=new MenuTypeboimpl();
		List<MenuType> item=mt.getTypes(mmtid);
		int mmtids=0;
		for(MenuType rs:item) {
			mmtids=(int)rs.getMtid();
		}
		String mname=request.getParameter("mname");
		float mprice=Float.parseFloat(request.getParameter("mprice"));
		float mdiscount=Float.parseFloat(request.getParameter("mdiscount"));
		String mdate=request.getParameter("mdate");
		Date date=null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(mdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Timestamp datetime = new java.sql.Timestamp(date.getTime());
		int mstatus=Integer.parseInt(request.getParameter("status"));
		int mid=Integer.parseInt(request.getParameter("mid"));
		if(mb.updateMenu(mmtids, mname, mprice, mdiscount, datetime, mstatus, mid)>0) {
			try {
				response.getWriter().print("{\"msg\":\"修改成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//添加
	public void insertMenu(HttpServletRequest request, HttpServletResponse response) {
		String mmtid=request.getParameter("mmtid");
		MenuTypeboimpl mt=new MenuTypeboimpl();
		List<MenuType> list=new ArrayList<MenuType>();
		List<MenuType> item=mt.getTypes(mmtid);
		int mmtids=0;
		for(MenuType rs:item) {
			mmtids=(int)rs.getMtid();
		}
		String mname=request.getParameter("mname");
		float mprice=Float.parseFloat(request.getParameter("mprice"));
		float mdiscount=Float.parseFloat(request.getParameter("mdiscount"));
		String mdate=request.getParameter("mdate");
		Date date=null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(mdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Timestamp datetime = new java.sql.Timestamp(date.getTime());
		int mstatus=Integer.parseInt(request.getParameter("status"));
		if(mb.insertMenu(mmtids, mname, mprice, mdiscount, datetime, mstatus)>0){
			try {
				response.getWriter().print("{\"msg\":\"添加成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//删除多项
	public void deleteAll(HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		String[] arr=mid.split(",");
		int j=0;
		for(int i=0;i<arr.length;i++) {
			int ids=Integer.parseInt(arr[i]);
			mb.deleteMenu(ids);
			j++;
		}
		if(j==arr.length) {
			try {
				response.getWriter().print("{\"msg\":\"删除成功\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//查询类型
	public void selectTypeAll(HttpServletRequest request, HttpServletResponse response) {
		MenuTypeboimpl mt=new MenuTypeboimpl();
		List<MenuType> list=new ArrayList<MenuType>();
		int mtParentClass=Integer.parseInt(request.getParameter("mtParentClass"));
		List<MenuType> item=mt.getType(mtParentClass);
		for(MenuType rs:item) {
			list.add(new MenuType(rs.getMtid(),rs.getMtname(),rs.getMtParentClass())
					);
		}
		try {
			response.getWriter().print(JsonUtils.toJson(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
