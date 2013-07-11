package com.asiainfo.hya.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;

public class DB {

	// singleton
	private static DB db = null;

	private Connection conn;
	private Statement stat;

	/**
	 * ExecutePreparedstatment SQL 
	 * @param sql 创建preparedstatment的SQL
	 * @param callback 执行过程的回调函数
	 * @return List<DynaBean>
	 * @throws SQLException
	 */
	public List<DynaBean> executePreparedstatmentSQL(String sql, DBPstatCallBack callback) throws SQLException {
		PreparedStatement pstat = conn.prepareStatement(sql);
		List<DynaBean> result = callback.executeQuery(pstat);
		pstat.close();
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List executeSQL(String sql) {
		if (null == sql) {
			return null;
		}
		try {
			ResultSet rs = null;
			rs = stat.executeQuery(sql);
			RowSetDynaClass dc = new RowSetDynaClass(rs);
			rs.close();
			return dc.getRows();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// singleton
	public static DB getDBInstance() {
		try {
			if (db == null) {
				db = new DB();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}

	private DB() throws SQLException {
		this.conn = getMYSQLCon();
		this.stat = this.conn.createStatement();
	}

	// MYSQL CONNECTION BUILD METHOD
	private static Connection getMYSQLCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection(
							"jdbc:mysql://10.1.139.179:3306/suiqa?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true",
							"portal", "qwe123");
			/*
			 * "jdbc:mysql://192.168.1.100:3306/suiqa?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true"
			 * , "zhaowei", "110119120");
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		getMYSQLCon();
	}

}
