package com.asiainfo.hya;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.asiainfo.hya.util.DateUtil;
import com.asiainfo.hya.util.db.DB;

public class UserList {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		SXSSFWorkbook wb = new SXSSFWorkbook();
		// Sheet sh = wb.createSheet();
		// database
		DB db = DB.getDBInstance();
		//
		List<DynaBean> provList = db.executeSQL(" select cityid, cityname from user_city where parentid = 999 ");
		Map<String, Sheet> provSheetMap = new HashMap<String, Sheet>();
		for (DynaBean prov : provList) {
			Sheet sheet = wb.createSheet(String.valueOf(prov.get("cityname")));
			provSheetMap.put(String.valueOf(prov.get("cityid")).substring(0, 3), sheet);
			// write header of table
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("姓名");
			row.createCell(1).setCellValue("账号");
			row.createCell(2).setCellValue("部门");
			row.createCell(3).setCellValue("职务");
			row.createCell(4).setCellValue("开户时间");
//			row.createCell(5).setCellValue("开户单类型");
		}

		// prov 为999或是19999 当做 10000来处理
		provSheetMap.put("999", provSheetMap.get("100"));
		provSheetMap.put("199", provSheetMap.get("100"));

		List<DynaBean> userList = db.executeSQL(" " + "	select u.userid, u.username, u.cityid, u.createtime, "
				+ "		uc.title as deptname, ud.title as dutyname  from user_user u "
				+ "		left join user_duty ud on ud.dutyid = u.dutyid "
				+ "		left join user_company uc on uc.deptid = u.departmentid "
				+ "			left join user_group_map ugm on u.userid = ugm.userid  "
				+ "		where ugm.group_id <> '8a819a85389986d40138d68633810001'");
		System.out.println(userList.size());
		for (int rownum = 0; rownum < userList.size(); rownum++) {
			// user info
			DynaBean user = userList.get(rownum);
			String prov = String.valueOf(user.get("cityid")).substring(0, 3);
			// System.out.println("user prov is " + prov);
			// choose sheet
			Row row = provSheetMap.get(prov).createRow(provSheetMap.get(prov).getLastRowNum() + 1);

			row.createCell(0).setCellValue(String.valueOf(user.get("username")));
			row.createCell(1).setCellValue(String.valueOf(user.get("userid")));
			row.createCell(2).setCellValue(String.valueOf(user.get("deptname")));
			row.createCell(3).setCellValue(String.valueOf(user.get("dutyname")));
			row.createCell(4).setCellValue(DateUtil.date2Str((Date)user.get("createtime"), "yyyy-MM-dd"));
			// if (isScannerUser(String.valueOf(user.get("username")), prov) > 0
			// || isScannerUser1(String.valueOf(user.get("username")), prov) >
			// 0) {
			// row.createCell(5).setCellValue(" 纸质开户单扫描类型");
			// } else if (isECreate(String.valueOf(user.get("username")), prov)
			// > 0) {
			// row.createCell(5).setCellValue("  电子开户单类型  ");
			// } else {
			// row.createCell(5).setCellValue(" -- ");
			// }
		}

		File userListFile = new File("D:\\一经门户用户列表.xlsx");
		try {
			if (!userListFile.exists()) {
				userListFile.createNewFile();
			}
			wb.write(new FileOutputStream(userListFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("use time " + (long) (System.currentTimeMillis() - starttime) + "ms");
	}

	/**
	 * 扫描件开户类型
	 * 
	 * @param username
	 * @param cityId
	 * @return
	 */
	private static int isScannerUser(String username, String cityId) {
		String sql = " select * from jt_accounts_single where username = '" + username + "' " + " and cityid like '"
				+ cityId + "%' ";
		return DB.getDBInstance().executeSQL(sql).size();
	}
	
	/**
	 * 扫描件开户类型
	 * 
	 * @param username
	 * @param cityId
	 * @return
	 */
	private static int isScannerUser1(String username, String cityid) {
		String sql = " select tmp.* from zhaowei.zhanggd_user_tmp tmp " +
				" left join suiqa.user_city uc on uc.CITYNAME = tmp.cityname" +
				"  where tmp.username = '" + username + "' " + " and uc.cityid like '"
				+ cityid + "%' ";
		return DB.getDBInstance().executeSQL(sql).size();
	}

	/**
	 * 电子开户类型
	 * 
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
