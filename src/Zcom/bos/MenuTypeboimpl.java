package Zcom.bos;

import java.util.List;

import Zcom.daos.MenuTypedaoimpl;
import Zcom.vos.MenuType;

public class MenuTypeboimpl {
	MenuTypedaoimpl mt=new MenuTypedaoimpl();
	public List<MenuType> getType(int mtParentClass){
		return mt.getType(mtParentClass);
	}
	public List<MenuType> getTypes(String mtname){
		return mt.getTypes(mtname);
	}
}
