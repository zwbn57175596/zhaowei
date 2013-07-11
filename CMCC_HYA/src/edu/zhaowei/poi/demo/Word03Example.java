package edu.zhaowei.poi.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Word03Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("D:\\temp\\zhaowei\\createWork\\template\\（集团公司）开户申请表.doc");
			POIFSFileSystem pfSys = new POIFSFileSystem(fis);
			HWPFDocument hwpf = new HWPFDocument(pfSys);
			Range range = hwpf.getRange(); // 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			int tbIndex = 0;
			while (it.hasNext()) {
				Table tb = (Table) it.next();
				for (int i = 0; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i); // 取得行
					for (int j = 0; j < tr.numCells(); j++) {
						TableCell td = tr.getCell(j);// 取得列
						for (int k = 0; k < td.numParagraphs(); k++) {
							Paragraph para = td.getParagraph(k);
							String s = para.text();
							System.out.println("tbIndex: " + tbIndex + ";  rowIndex : " + i + ";  cellIndex : " + j + ";  Content is : '" + s + "' ");
						} // end for
					} // end for
				} // end for
				
				tbIndex ++;
			} // end while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try catch block
	}// end main

}
