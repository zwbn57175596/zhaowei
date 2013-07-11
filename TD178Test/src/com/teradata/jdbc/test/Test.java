package com.teradata.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static Connection getConn() {
		Connection con = null;
		try {
			Class.forName("com.ncr.teradata.TeraDriver");
			con = DriverManager.getConnection(
					"jdbc:teradata://10.1.139.178/CHARSET=ASCII,CLIENT_CHARSET=GBK,DATABASE=aieview", "aiap",
					"Asia_289..");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		try {
			Connection con = getConn();
			Statement statement = con.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT kpi_period_id,kpi_period_name,kpi_period_alias,kpi_period_code,kpi_period_level,kpi_period_date,kpi_period_alert_id,display_order FROM NEW_KPI_PERIOD ORDER BY display_order");
			while (rs.next()) {
				System.out.println(rs.getString(0) + ", " + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3)+ ", " + rs.getString(4)+ ", " + rs.getString(5)+ ", " + rs.getString(6)+ ", " + rs.getString(7)+ ", " + rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
