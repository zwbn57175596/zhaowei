package edu.zhaowei.outofmemory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestHeapOutOfMemory {
	
	
	public void readfile() {
//		File file = new File();
		try {
			FileInputStream fis = new FileInputStream("D:\\worksapces\\mat_workspace\\TDSERVER\\heap.bin");
			FileOutputStream fos = new FileOutputStream("D:\\heap.bin");
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outString () throws InterruptedException {
		StringBuffer sb = new StringBuffer();
		while (true) {
//			Thread.sleep(1);
			sb.append("1234567890123456");
		}
	}
	
	public static void main(String[] args) {
		TestHeapOutOfMemory test = new TestHeapOutOfMemory();
		try {
			test.outString();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}

/*class DoThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}*/
