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
	
	//���
	public String addToCart(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.addToCart(id, menuid).serialize();
	}
	//����
	public String selfReduction(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.selfReduction(id, menuid).serialize();
	}
	//�鿴��Ա���ﳵ
	public String showCart(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.showCart(id).serialize();
	}
	
	//��ջ�Ա���ﳵ
	public String emptyVipCart(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.emptyVipCart(id).serialize();
	}
	
	//��Ʒ��ϸ
	public String menuDetailed(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		Takeawaybo bo = new Takeawaybo();
		return bo.menuDetailed(menuid).serialize();
	}
	
	//��Ʒ��������
	public String menuCount(HttpServletRequest request,
			HttpServletResponse response) {
		int menuid = Integer.parseInt(request.getParameter("menuid"));
		Takeawaybo bo = new Takeawaybo();
		return bo.menuCount(menuid);
	}
	
	//ȷ�϶���
	public String confirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		//��ԱId
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		//�Ƿ��Ʊ
		boolean oinvoice = Boolean.parseBoolean(request.getParameter("oinvoice"));
		//������ַid
		int address = Integer.parseInt(request.getParameter("address"));
		//��ע
		String oremark = request.getParameter("oremark");
		Takeawaybo bo = new Takeawaybo();
		return bo.addOrder(id, oinvoice, address, oremark).serialize();
	}
	
	//��������
	public String checkOut(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("����checkout��");
		Takeawaybo bo = new Takeawaybo();
		String oNumber = request.getParameter("oNumber");
		return bo.checkOut(oNumber).serialize();
	}

	//Ĭ�ϵ�ַ 1.
	public String defaultLocations(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.defaultAddress(id).serialize();
	}
	
	//��Ա��ַ 2.�����ַ
	public String vipAddress(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		System.out.println(id);
		Takeawaybo bo = new Takeawaybo();
		return bo.vipAddress(id).serialize();
	}
	
	//������ַ 3.���
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
	
	//��ѯ��Ա��Ϣ1.====
	public String vipInfo(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipInfo(id).serialize();
	}
	
	//��ѯ��Ա�ȼ�
	public String vipType(HttpServletRequest request,
			HttpServletResponse response) {
		int idtype = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVvtid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipType(idtype).serialize();
	}
	
	/**�鿴��Աȫ������*/
	public String vipAllorders(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		Takeawaybo bo = new Takeawaybo();
		return bo.vipAllOrders(id).serialize();
	}
	
	/**�鿴�����Ŷ���*/
	public String order(HttpServletRequest request,
			HttpServletResponse response) {
		String oNumber = request.getParameter("oNumber");
		Takeawaybo bo = new Takeawaybo();
		return bo.orders(oNumber).serialize();
	}
	
	/**�鿴��Ա״̬����*/
	public String vipOrders(HttpServletRequest request,
			HttpServletResponse response) {
		int id = ((Vipvo)request.getSession().getAttribute("Vipvo")).getVid();
		//�������=0	//�Ѹ����=1	//����ɶ���=2
		int ostatus = Integer.parseInt(request.getParameter("ostatus"));
		Takeawaybo bo = new Takeawaybo();
		return bo.vipOrders(id, ostatus).serialize();
	}
	
	/**���Ա�����˵�*///coNumber Ϊ����
	public String ordersMenu(HttpServletRequest request,
			HttpServletResponse response) {
		String number = request.getParameter("oNumber");
		Takeawaybo bo = new Takeawaybo();
		return bo.ordersMenu(number).serialize();
	}
	
	
}
