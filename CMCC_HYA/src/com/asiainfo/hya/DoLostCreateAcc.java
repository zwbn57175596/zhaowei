package com.asiainfo.hya;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.asiainfo.hya.util.db.DB;
import com.asiainfo.hya.util.db.DBPstatCallBack;

//������ʧ�Ŀ�����
public class DoLostCreateAcc {

	/*
	 * private static POIFSFileSystem JT_CREATE_TEMPLATE = null; private static
	 * POIFSFileSystem PROV_CREATE_TEMPLATE = null;
	 */
	private static Set<String> replaceKeySet = null;

	static {
		replaceKeySet = new HashSet<String>();
		replaceKeySet.add("userid");
		replaceKeySet.add("cityname");
		replaceKeySet.add("company");
		replaceKeySet.add("username");
		replaceKeySet.add("mobile");
		replaceKeySet.add("department");
		replaceKeySet.add("duty");
		/*
		 * try { JT_CREATE_TEMPLATE = PROV_CREATE_TEMPLATE = new
		 * POIFSFileSystem(new FileInputStream(
		 * "D:\\temp\\zhaowei\\createWork\\template\\��ʡ��˾�����������.doc")); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List<DynaBean> lostUserInfo = getLostUserInfo(getLostUserId());
		List<String> temp = new ArrayList<String>();
		temp.add("linduo@fj.cmcc");
		List<DynaBean> lostUserInfo = getLostUserInfo(temp);
		for (DynaBean bean : lostUserInfo) {
			// System.out.println(bean.get("username"));
			if (String.valueOf(bean.get("cityname")).equals("����")) {
				createJTCreate(bean);
			} else {
				createProvCreate(bean);
			}
		}
	}

	public static void createProvCreate(DynaBean bean) {
		try {
			OPCPackage opcPackage = POIXMLDocument
					.openPackage("D:\\temp\\zhaowei\\�������\\createWork\\template\\��ʡ��˾�����������.docx");
			XWPFDocument jt_new1 = new XWPFDocument(opcPackage);
			Iterator<XWPFTable> iter_tb = jt_new1.getTablesIterator();
			while (iter_tb.hasNext()) {
				XWPFTable tb = iter_tb.next();
				for (XWPFTableRow row : tb.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph para : cell.getParagraphs()) {
							for (XWPFRun run : para.getRuns()) {
								if (run.getText(run.getTextPosition()) == null)
									continue;
								String s = run.getText(run.getTextPosition());
								final String old = s;
								for (String key : replaceKeySet) {
									if (s.indexOf(key) >= 0) {
										s = s.replace(key, String.valueOf(bean.get(key)));
									}
									if (!old.equals(s)) {// �б仯
										run.setText(s, 0);
										System.out.println("old:" + old + "->" + "s:" + s);
									}
								}
							}
						}
					}
				}
			}
			FileOutputStream fos = new FileOutputStream("D:\\temp\\zhaowei\\�������\\createWork\\ʡ��˾������\\"
					+ String.valueOf(bean.get("cityname")) + "_" + String.valueOf(bean.get("username")) + ".docx");
			jt_new1.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createJTCreate(DynaBean bean) {
		try {
			OPCPackage opcPackage = POIXMLDocument
					.openPackage("D:\\temp\\zhaowei\\�������\\createWork\\template\\�����Ź�˾�����������.docx");
			XWPFDocument jt_new1 = new XWPFDocument(opcPackage);
			Iterator<XWPFTable> iter_tb = jt_new1.getTablesIterator();
			while (iter_tb.hasNext()) {
				XWPFTable tb = iter_tb.next();
				for (XWPFTableRow row : tb.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph para : cell.getParagraphs()) {
							for (XWPFRun run : para.getRuns()) {
								if (run.getText(run.getTextPosition()) == null)
									continue;
								String s = run.getText(run.getTextPosition());
								final String old = s;
								for (String key : replaceKeySet) {
									if (s.indexOf(key) >= 0) {
										s = s.replace(key, String.valueOf(bean.get(key)));
									}
									if (!old.equals(s)) {// �б仯
										run.setText(s, 0);
										System.out.println("old:" + old + "->" + "s:" + s);
									}
								}
							}
						}
					}
				}
			}
			/*
			 * HWPFDocument jt_new = new HWPFDocument(new POIFSFileSystem(new
			 * FileInputStream(
			 * "D:\\temp\\zhaowei\\�������\\createWork\\template\\�����Ź�˾�����������.doc")));
			 * Range range = jt_new.getRange(); // �õ��ĵ��Ķ�ȡ��Χ TableIterator it =
			 * new TableIterator(range); int tbIndex = 0; while (it.hasNext()) {
			 * Table tb = (Table) it.next(); for (int i = 0; i < tb.numRows();
			 * i++) { TableRow tr = tb.getRow(i); // ȡ���� for (int j = 0; j <
			 * tr.numCells(); j++) { TableCell td = tr.getCell(j);// ȡ���� for
			 * (int k = 0; k < td.numParagraphs(); k++) { Paragraph para =
			 * td.getParagraph(k); String s = para.text(); final String old = s;
			 * for (String key : replaceKeySet) { if (s.indexOf(key) >= 0) { s =
			 * s.replace(key, String.valueOf(bean.get(key))); } } if
			 * (!old.equals(s)) {// �б仯 para.replaceText(old, s); s =
			 * para.text(); System.out.println("old:" + old + "->" + "s:" + s);
			 * } } // end for } // end for } // end for
			 * 
			 * tbIndex++; }
			 */// end while
				// writeFirstTable
			/*
			 * while (it.hasNext()) { Table table = it.next();
			 * table.getRow(0).getCell
			 * (2).getParagraph(0).insertBefore(String.valueOf
			 * (bean.get("userid")));
			 * table.getRow(1).getCell(2).getParagraph(0).
			 * insertBefore(String.valueOf(bean.get("department")));
			 * table.getRow
			 * (7).getCell(2).getParagraph(0).insertBefore(String.valueOf
			 * (bean.get("username")));
			 * table.getRow(7).getCell(4).getParagraph(0
			 * ).insertBefore(String.valueOf(bean.get("mobile")));
			 * table.getRow(8
			 * ).getCell(2).getParagraph(0).insertBefore(String.valueOf
			 * (bean.get("department")));
			 * table.getRow(8).getCell(4).getParagraph
			 * (0).insertBefore(String.valueOf(bean.get("duty"))); break; }
			 */
			FileOutputStream fos = new FileOutputStream("D:\\temp\\zhaowei\\�������\\createWork\\���ſ�����\\"
					+ String.valueOf(bean.get("department")) + "_" + String.valueOf(bean.get("username")) + ".docx");
			jt_new1.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���û���Ϣ
	 * 
	 * @param lostUserIdList
	 * @return
	 */
	private static List<DynaBean> getLostUserInfo(final List<String> lostUserIdList) {
		List<DynaBean> result = new ArrayList<DynaBean>();
		String sql = " select u.USERID as userid, u.USERNAME as username, u.MOBILEPHONE as mobile, "
				+ " 			case when ucp.CITYID = 999 then uc.CITYNAME else concat(ucp.CITYNAME, '--', uc.CITYNAME) end as cityname, "
				+ "			uco.TITLE as department, ud.TITLE as duty " + " from user_user u "
				+ "		left join user_city uc on u.CITYID = uc.CITYID "
				+ "		left join user_city ucp on uc.PARENTID = ucp.CITYID"
				+ "		left join user_company uco on u.DEPARTMENTID = uco.DEPTID "
				+ "		left join user_duty ud on u.DUTYID = ud.DUTYID " + " where u.USERID = ? ";
		try {
			result = DB.getDBInstance().executePreparedstatmentSQL(sql, new DBPstatCallBack() {
				@SuppressWarnings("unchecked")
				@Override
				public List<DynaBean> executeQuery(PreparedStatement stat) {
					List<DynaBean> temp = new ArrayList<DynaBean>();
					try {
						for (String userid : lostUserIdList) {
							stat.setString(1, userid);
							temp.addAll(new RowSetDynaClass(stat.executeQuery()).getRows());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return temp;
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �õ���ʧ���������û�ID
	 * 
	 * @return
	 */
	private static List<String> getLostUserId() {
		List<String> result = new ArrayList<String>();
		;
		try {
			Iterator<Row> iter = new XSSFWorkbook(new FileInputStream(new File(
					"D:\\temp\\zhaowei\\�������\\createWork\\lostUserId.xlsx"))).getSheetAt(0).rowIterator();
			while (iter.hasNext()) {
				result.add(iter.next().getCell(0).getStringCellValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
