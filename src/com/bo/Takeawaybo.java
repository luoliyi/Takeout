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

	// 加菜
	public Responsevo addToCart(int id, int menuid) {
		// 判断是否存在
		ShoppingCartdao dao = new ShoppingCartdao();
		ShoppingCartvo vo = dao.selectMenu(id, menuid);
		if (vo != null) {// 存在
			int count = dao.append(vo.getScid(), 1);
			if (count > 0) {
				return new Responsevo(0);
			} else {
				return new Responsevo(1);
			}
		} else {//不存在
			int count = dao.insert(id, menuid);
			if (count > 0) {
				return new Responsevo(0);
			} else {
				return new Responsevo(1);
			}
		}
	}

	// 减菜
	public Responsevo selfReduction(int id, int scmenu) {
		ShoppingCartdao dao = new ShoppingCartdao();
		ShoppingCartvo vo = dao.selectMenu(id, scmenu);
		int count = 0;
		if (vo.getScquantity() > 1) {
			count = dao.append(vo.getScid(), -1);
		} else {
			// 删除
			count = dao.delete(vo.getScid());
		}
		if (count > 0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}

	// 查询会员购物车
	public Responsevo showCart(int id) {
		ShoppingCartdao dao = new ShoppingCartdao();
		return new Responsevo(0, dao.selectVipMenu(id));
	}

	// 菜品详细
	public Responsevo menuDetailed(int id) {
		Menudao dao = new Menudao();
		return new Responsevo(0, dao.selectTypeMenu(id));
	}

	// 清空会员购物车
	public Responsevo emptyVipCart(int id) {
		ShoppingCartdao dao = new ShoppingCartdao();
		int count = dao.deleteVip(id);
		if (count > 0) {
			return new Responsevo(0);
		} else {
			return new Responsevo(1);
		}
	}

	// 获取默认地址
	public Responsevo defaultAddress(int id) {
		Locationsdao dao = new Locationsdao();
		return new Responsevo(0, dao.selectDefault(id));
	}

	// 获取会员地址
	public Responsevo vipAddress(int id) {
		Locationsdao dao = new Locationsdao();
		return new Responsevo(0, dao.selectVip(id));
	}
	
	//新添地址
	public Responsevo addLocations(int svid, String sname, String lsex
			, String sphone, String saddress) {
		Locationsdao dao = new Locationsdao();
		if(dao.insert(svid, sname, lsex, sphone, saddress)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}

	// 添加订单数据 参数： 会员id, 发票,地址id,备注
	public Responsevo addOrder(int vipid, boolean oinvoice
			, int address, String oremark) {
		// 订单号
		String oNumber = orderNumber();
		// 订购时间 new Date;
		java.sql.Timestamp oorderDate = new java.sql.Timestamp(new Date().getTime());
		ShoppingCartdao dao = new ShoppingCartdao();
		List<ShoppingCartvo> list = dao.selectVipMenu(vipid);//获取购物车数据
		// 获取总金额
		BigDecimal amount = salesAmount(list);
		Orderdao orderdao = new Orderdao();
		int count = orderdao.insert(oNumber, oorderDate, amount, 
				oinvoice, vipid, address, oremark, 0);
		if(count>0) {
			if(addConsumption(list,oNumber)==list.size()) {
				emptyVipCart(vipid);//清空购物车
				Ordersvo vo =orderdao.selectOrders(oNumber);
				vo.setoNumber(oNumber);
				return new Responsevo(0, vo);
			} else {
				Consumptiondao condao = new Consumptiondao();
				condao.delete(oNumber);//清空消费记录
				orderdao.delete(oNumber);//清空订单
				return new Responsevo(1, "订购失败");
			}
		} else {
			return new Responsevo(1, "订购失败");
		}
	}

	// 生成订单号
	public String orderNumber() {
		Date current = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String currenDate = df.format(current) + randomNumber(6);
		return currenDate;
	}

	// 随机生成数字
	public String randomNumber(int lenght) {
		String randomString = "";
		for (int i = 0; i < lenght; i++) {
			randomString += "" + (int) (1 + Math.random() * (10 - 1 + 1));
		}
		return randomString;
	}

	// 获取菜品销售数量
	public String menuCount(int menuid) {
		Consumptiondao dao = new Consumptiondao();
		return "" + dao.menuCount(menuid);
	}

	// 获取销售金额
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

	// 向消费表添加购物车记录
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

	//订单结账
	public Responsevo checkOut(String oNumber) {
		Orderdao dao = new Orderdao();
		if(dao.update(oNumber, 1)>0) {
			return new Responsevo(0);
		}
		return new Responsevo(1);
	}
	
	//获取会员信息
	public Responsevo vipInfo(int id) {
		Vipdao vip = new Vipdao();
		Vipvo vo = vip.selectid(id);
		if(vo!=null) {
			return new Responsevo(0,vo);
		}
		return new Responsevo(1);
	}
	
	//获取会员等级
	public Responsevo vipType(int id) {
		VipTypedao vipType = new VipTypedao();
		VipTypevo vo =vipType.selectType(id);
		if(vo!=null) {
			return new Responsevo(0,vo);
		}
		return new Responsevo(1);
	}
	
	//全部订单
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
	

	//获取订单信息
	public Responsevo orders(String oNumber) {
		Orderdao dao = new Orderdao();
		Ordersvo vo = dao.selectOrders(oNumber);
		if(vo!=null) {
			return new Responsevo(0, vo);
		}
		return  new Responsevo(1);
	}	
	//待付款订单0	//已付款订单1	//已完成订单2
	public Responsevo vipOrders(int id, int ostatus) {
		Orderdao dao = new Orderdao();
		List<Ordersvo> listvo = dao.selectVipOrders(id,ostatus);
		if(listvo!=null) {
			return new Responsevo(0, listvo);
		}
		return  new Responsevo(1);
	}
	
	//查询订单相应餐单
	public Responsevo ordersMenu(String number) {
		Consumptiondao dao = new Consumptiondao();
		List<Consumptionvo> listvo = dao.selectOrders(number);
		
		if(listvo!=null){
			return new Responsevo(0, listvo);
		}
		return new Responsevo(1);
	}
	
	
}
