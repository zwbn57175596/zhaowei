package edu.zhaowei.outofmemory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestStackOverFlow {
	
	private int infinite() {
		return infinite();
	}
	
	public static void main(String[] args) {
		TestStackOverFlow out = new TestStackOverFlow();
		try {
			BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
			while (!"start".equals(reader.readLine()));
			out.infinite();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
