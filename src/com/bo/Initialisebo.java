package com.bo;

import com.dao.MenuTypedao;
import com.dao.Menudao;
import com.vo.Responsevo;

/**
 * ��ʼҳ����Ϣ����ҵ���
 * @author ��˳
 *
 */
public class Initialisebo {

	//��Ʒ�˵�����
	public Responsevo typeMenu(){
		MenuTypedao dao = new MenuTypedao();
		return new Responsevo(0,"", dao.menuType(0));
	}
	
	//��Ʒ
	public Responsevo menu(int menuTypeid) {
		Menudao dao = new Menudao();
		MenuTypedao menuTypedao = new MenuTypedao();
		return new Responsevo(0,"", dao.selectTypeMenu(menuTypedao.menuid(menuTypeid)));
	}
	
	//�²�Ʒ
	public Responsevo selectNewMenu() {
		Menudao dao = new Menudao();
		return new Responsevo(0,"", dao.selectNewMenu());
	}
}
