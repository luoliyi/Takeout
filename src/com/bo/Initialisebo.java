package com.bo;

import com.dao.MenuTypedao;
import com.dao.Menudao;
import com.vo.Responsevo;

/**
 * 初始页面信息数据业务层
 * @author 大顺
 *
 */
public class Initialisebo {

	//菜品菜单类型
	public Responsevo typeMenu(){
		MenuTypedao dao = new MenuTypedao();
		return new Responsevo(0,"", dao.menuType(0));
	}
	
	//菜品
	public Responsevo menu(int menuTypeid) {
		Menudao dao = new Menudao();
		MenuTypedao menuTypedao = new MenuTypedao();
		return new Responsevo(0,"", dao.selectTypeMenu(menuTypedao.menuid(menuTypeid)));
	}
	
	//新菜品
	public Responsevo selectNewMenu() {
		Menudao dao = new Menudao();
		return new Responsevo(0,"", dao.selectNewMenu());
	}
}
