package com.control;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		int a = 2;
		BigDecimal b1 = new BigDecimal(3);
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < 3; i++) {
			System.out.println(result);
			result = result.add(b1.multiply(new BigDecimal(2)));
		}
		System.out.println(result);
	}
	
	
}
