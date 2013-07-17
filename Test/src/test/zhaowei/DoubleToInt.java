package test.zhaowei;

import java.math.BigInteger;

public class DoubleToInt {
	
	public static void main(String[] args) {
		Double dl = new Double("1.3600980512E10");
		System.out.println(dl);
		System.out.println(dl.longValue());
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		
		int i = 1;
		System.out.println(i);
		System.out.println(i << 1);
		System.out.println(i << 2);
		System.out.println(i << 31);
	}

}
