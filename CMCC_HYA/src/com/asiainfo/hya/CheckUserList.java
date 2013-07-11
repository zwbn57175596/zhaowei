package com.asiainfo.hya;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.asiainfo.hya.util.db.DB;

public class CheckUserList {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		File file = new File("D:\\temp\\缺少开户单用户信息列表.xlsx");
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(new FileInputStream(file));
			// Sheet sh = wb.createSheet();
			// database
			DB db = DB.getDBInstance();
			XSSFSheet sheet = wb.getSheet("缺少开户单用户信息列表");
			
			Map<String, String> provMap = new HashMap<String, String>();
			String sql = " select cityid, cityname from user_city where parentid = 999 ";
			List<DynaBean> result = db.executeSQL(sql);
			for (DynaBean dynaBean : result) {
				provMap.put(String.valueOf(dynaBean.get("cityname")), String.valueOf(dynaBean.get("cityid")));
			}

			for (int rownum = 0; rownum < sheet.getLastRowNum(); rownum++) {
				
				XSSFRow row = sheet.getRow(rownum);
				String username = row.getCell(2).getStringCellValue();
				String provName = row.getCell(0).getStringCellValue();
				System.out.println(username + ":" + String.valueOf(provMap.get(provName)).substring(0, 3));
				if (isScannerUser(username, String.valueOf(provMap.get(provName)).substring(0, 3)) > 0) {
					row.createCell(5).setCellValue(" 纸质开户单扫描类型");
				} else if (isECreate(username, String.valueOf(provMap.get(provName)).substring(0, 3)) > 0) {
					row.createCell(5).setCellValue("  电子开户单类型  ");
				} else {
					row.createCell(5).setCellValue(" -- ");
				}
			}
			wb.write(new FileOutputStream(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("use time " + (long)(System.currentTimeMillis() - starttime) + "ms");
	}

	/**
	 * 扫描件开户类型
	 * @param username
	 * @param cityId
	 * @return
	 */
	private static int isScannerUser(String username, String cityId) {
		String sql = " select * from jt_accounts_single where username = '" + username + "' " + " and cityid like '"
				+ cityId + "%' and accessory_name not like '%地市%' ";
		return DB.getDBInstance().executeSQL(sql).size();
	}
	
	

	/**
	 * 电子开户类型
	 * @param username
	 * @param cityid
	 * @return
	 */
	private static int isECreate(String username, String cityid) {
		String sql = "select cre.USERNAME, cre.CITYID, cre.create_time from jt_create_user_info cre 	"
				+ "  left join jt_audit_result_info res on cre.ID = res.JT_HAFT_AUDIT_ID "
				+ "  where res.AUDIT_RES_TYPE = 2 and res.AUDITOR_ID = 'caiyanliang' and res.ISAGREE = 0 "
				+ "  and cre.USERNAME = '" + username + "' and cre.CITYID like '" + cityid + "%' ";
		return DB.getDBInstance().executeSQL(sql).size();
	}
}
