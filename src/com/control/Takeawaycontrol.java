package com.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Takeawaybo;
import com.vo.Vipvo;

/**
 * Servlet implementation class Takeawaycontrol
 */
@WebServlet("/Takeawaycontrol.do")
public class Takeawaycontrol extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//点菜
	public String addToCart(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.addToCart(id, menuid).serialize();
	}
	//减菜
	public String selfReduction(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.selfReduction(id, menuid).serialize();
	}
	//查看会员购物车
	public String showCart(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.showCart(id).serialize();
	}
	
	//清空会员购物车
	public String emptyVipCart(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.emptyVipCart(id).serialize();
	}
	
	//菜品详细
	public String menuDetailed(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		Takeawaybo bo = new Takeawaybo();
		return bo.menuDetailed(menuid).serialize();
	}
	
	//菜品消费数量
	public String menuCount(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		Takeawaybo bo = new Takeawaybo();
		return bo.menuCount(menuid);
	}
	
	//确认订单
	public String confirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		//会员Id
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		//是否打发票
		boolean oinvoice = Boolean.parseBoolean(request.getParameter("oinvoice"));
		//订单地址id
		int address = Integer.parseInt(request.getParameter("address"));
		//备注
		String oremark = request.getParameter("oremark");
		Takeawaybo bo = new Takeawaybo();
		return bo.addOrder(id, oinvoice, address, oremark).serialize();
	}
	
	//订单结账
	public String checkOut(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进来checkout了");
		Takeawaybo bo = new Takeawaybo();
		String oNumber = request.getParameter("oNumber");
		return bo.checkOut(oNumber).serialize();
	}

	//默认地址 1.
	public String defaultLocations(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.defaultAddress(id).serialize();
	}
	
	//会员地址 2.更多地址
	public String vipAddress(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		System.out.println(id);
		Takeawaybo bo = new Takeawaybo();
		return bo.vipAddress(id).serialize();
	}
	
	//新增地址 3.添加
	public String addLocations(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		String sname = request.getParameter("name");
		String lsex = request.getParameter("sex");
		String sphone = request.getParameter("phone");
		String saddress = request.getParameter("saddress");
		System.out.println(sname+lsex+sphone+saddress);
		Takeawaybo bo = new Takeawaybo();
		return bo.addLocations(id, sname, lsex, sphone, saddress).serialize();
	}
	
	//查询会员信息1.====
	public String vipInfo(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipInfo(id).serialize();
	}
	
	//查询会员等级
	public String vipType(HttpServletRequest request,
			HttpServletResponse response) {
		int idtype = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVvtid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipType(idtype).serialize();
	}
	
	/**查看会员全部订单*/
	public String vipAllorders(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipAllOrders(id).serialize();
	}
	
	/**查看订单号订单*/
	public String order(HttpServletRequest request,
			HttpServletResponse response) {
		String oNumber = request.getParameter("oNumber");
		Takeawaybo bo = new Takeawaybo();
		return bo.orders(oNumber).serialize();
	}
	
	/**查看会员状态订单*/
	public String vipOrders(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		//待付款订单=0	//已付款订单=1	//已完成订单=2
		int ostatus = Integer.parseInt(request.getParameter("ostatus"));
		Takeawaybo bo = new Takeawaybo();
		return bo.vipOrders(id, ostatus).serialize();
	}
	
	/**查会员订单菜单*///coNumber 为菜名
	public String ordersMenu(HttpServletRequest request,
			HttpServletResponse response) {
		String number = request.getParameter("oNumber");
		Takeawaybo bo = new Takeawaybo();
		return bo.ordersMenu(number).serialize();
	}
	
	
}
