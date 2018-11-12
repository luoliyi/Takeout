package com.bo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.Consumptiondao;
import com.dao.Locationsdao;
import com.dao.Menudao;
import com.dao.Orderdao;
import com.dao.ShoppingCartdao;
import com.dao.VipTypedao;
import com.dao.Vipdao;
import com.vo.Consumptionvo;
import com.vo.Ordersvo;
import com.vo.Responsevo;
import com.vo.ShoppingCartvo;
import com.vo.VipTypevo;
import com.vo.Vipvo;

public class Takeawaybo {

	// �Ӳ�
	public Responsevo addToCart(int id, int menuid) {
		// �ж��Ƿ����
		ShoppingCartdao dao = new ShoppingCartdao();
		ShoppingCartvo vo = dao.selectMenu(id, menuid);
		if (vo != null) {// ����
			int count = dao.append(vo.getScid(), 1);
			if (count > 0) {
				return new Responsevo(0);
			} else {
				return new Responsevo(1);
			}
		} else {//������
			int count = dao.insert(id, menuid);
			if (count > 0) {
				return new Responsevo(0);
			} else {
				return new Responsevo(1);
			}
		}
	}

	// ����
	public Responsevo selfReduction(int id, int scmenu) {
		ShoppingCartdao dao = new ShoppingCartdao();
		ShoppingCartvo vo = dao.selectMenu(id, scmenu);
		int count = 0;
		if (vo.getScquantity() > 1) {
			count = dao.append(vo.getScid(), -1);
		} else {
			// ɾ��
			count = dao.delete(vo.getScid());
		}
		if (count > 0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}

	// ��ѯ��Ա���ﳵ
	public Responsevo showCart(int id) {
		ShoppingCartdao dao = new ShoppingCartdao();
		return new Responsevo(0, dao.selectVipMenu(id));
	}

	// ��Ʒ��ϸ
	public Responsevo menuDetailed(int id) {
		Menudao dao = new Menudao();
		return new Responsevo(0, dao.selectTypeMenu(id));
	}

	// ��ջ�Ա���ﳵ
	public Responsevo emptyVipCart(int id) {
		ShoppingCartdao dao = new ShoppingCartdao();
		int count = dao.deleteVip(id);
		if (count > 0) {
			return new Responsevo(0);
		} else {
			return new Responsevo(1);
		}
	}

	// ��ȡĬ�ϵ�ַ
	public Responsevo defaultAddress(int id) {
		Locationsdao dao = new Locationsdao();
		return new Responsevo(0, dao.selectDefault(id));
	}

	// ��ȡ��Ա��ַ
	public Responsevo vipAddress(int id) {
		Locationsdao dao = new Locationsdao();
		return new Responsevo(0, dao.selectVip(id));
	}
	
	//�����ַ
	public Responsevo addLocations(int svid, String sname, String lsex
			, String sphone, String saddress) {
		Locationsdao dao = new Locationsdao();
		if(dao.insert(svid, sname, lsex, sphone, saddress)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}

	// ��Ӷ������� ������ ��Աid, ��Ʊ,��ַid,��ע
	public Responsevo addOrder(int vipid, boolean oinvoice
			, int address, String oremark) {
		// ������
		String oNumber = orderNumber();
		// ����ʱ�� new Date;
		java.sql.Timestamp oorderDate = new java.sql.Timestamp(new Date().getTime());
		ShoppingCartdao dao = new ShoppingCartdao();
		List<ShoppingCartvo> list = dao.selectVipMenu(vipid);//��ȡ���ﳵ����
		// ��ȡ�ܽ��
		BigDecimal amount = salesAmount(list);
		Orderdao orderdao = new Orderdao();
		int count = orderdao.insert(oNumber, oorderDate, amount, 
				oinvoice, vipid, address, oremark, 0);
		if(count>0) {
			if(addConsumption(list,oNumber)==list.size()) {
				emptyVipCart(vipid);//��չ��ﳵ
				Ordersvo vo =orderdao.selectOrders(oNumber);
				vo.setoNumber(oNumber);
				return new Responsevo(0, vo);
			} else {
				Consumptiondao condao = new Consumptiondao();
				condao.delete(oNumber);//������Ѽ�¼
				orderdao.delete(oNumber);//��ն���
				return new Responsevo(1, "����ʧ��");
			}
		} else {
			return new Responsevo(1, "����ʧ��");
		}
	}

	// ���ɶ�����
	public String orderNumber() {
		Date current = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String currenDate = df.format(current) + randomNumber(6);
		return currenDate;
	}

	// �����������
	public String randomNumber(int lenght) {
		String randomString = "";
		for (int i = 0; i < lenght; i++) {
			randomString += "" + (int) (1 + Math.random() * (10 - 1 + 1));
		}
		return randomString;
	}

	// ��ȡ��Ʒ��������
	public String menuCount(int menuid) {
		Consumptiondao dao = new Consumptiondao();
		return "" + dao.menuCount(menuid);
	}

	// ��ȡ���۽��
	public BigDecimal salesAmount(List<ShoppingCartvo> list) {
		BigDecimal result = new BigDecimal(0);
		for (ShoppingCartvo shoppingCartvo : list) {
			result = result.add(shoppingCartvo.getMprice()
					.multiply(new BigDecimal(shoppingCartvo.getScquantity()))
					);
		}
		result = result.add(new BigDecimal(6));
		return result;
	}

	// �����ѱ���ӹ��ﳵ��¼
	public int addConsumption(List<ShoppingCartvo> list, String oNumber) {
		int result = 0;
		Consumptiondao dao= new Consumptiondao();
		for (ShoppingCartvo vo : list) {
			if(dao.insert(oNumber, vo.getScmenu(), vo.getScquantity())>0) {
				result++;
			}
		}
		return result;
	}

	//��������
	public Responsevo checkOut(String oNumber) {
		Orderdao dao = new Orderdao();
		if(dao.update(oNumber, 1)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//��ȡ��Ա��Ϣ
	public Responsevo vipInfo(int id) {
		Vipdao vip = new Vipdao();
		Vipvo vo = vip.selectid(id);
		if(vo!=null) {
			return new Responsevo(0,vo);
		}
		return new Responsevo(1);
	}
	
	//��ȡ��Ա�ȼ�
	public Responsevo vipType(int id) {
		VipTypedao vipType = new VipTypedao();
		VipTypevo vo =vipType.selectType(id);
		if(vo!=null) {
			return new Responsevo(0,vo);
		}
		return new Responsevo(1);
	}
	
	//ȫ������
	public Responsevo vipAllOrders(int id) {
		Orderdao dao = new Orderdao();
		List<Ordersvo> listvo = dao.selectAllVipOrders(id);
		if(listvo!=null) {
			for (Ordersvo o : listvo) {
				System.out.println(o.toString());
			}
			return new Responsevo(0, listvo);
		}
		return  new Responsevo(1);
	}
	

	//��ȡ������Ϣ
	public Responsevo orders(String oNumber) {
		Orderdao dao = new Orderdao();
		Ordersvo vo = dao.selectOrders(oNumber);
		if(vo!=null) {
			return new Responsevo(0, vo);
		}
		return  new Responsevo(1);
	}	
	//�������0	//�Ѹ����1	//����ɶ���2
	public Responsevo vipOrders(int id, int ostatus) {
		Orderdao dao = new Orderdao();
		List<Ordersvo> listvo = dao.selectVipOrders(id,ostatus);
		if(listvo!=null) {
			return new Responsevo(0, listvo);
		}
		return  new Responsevo(1);
	}
	
	//��ѯ������Ӧ�͵�
	public Responsevo ordersMenu(String number) {
		Consumptiondao dao = new Consumptiondao();
		List<Consumptionvo> listvo = dao.selectOrders(number);
		
		if(listvo!=null){
			return new Responsevo(0, listvo);
		}
		return new Responsevo(1);
	}
	
	
}
