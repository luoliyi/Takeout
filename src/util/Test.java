package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(orderNumber());
	}

	public static String orderNumber() {
		 Date current=new Date();    
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
		 String currenDate = df.format(current)+randomNumber();
		return currenDate;
	}
	
	//随机生成数字
	public static String randomNumber() {
		String randomString = "";
		for(int i=0; i<4; i++) {
			randomString = ""+(int)(1+Math.random()*(10-1+1));
		}		
		return randomString;
	}
}
