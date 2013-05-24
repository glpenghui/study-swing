package com;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接
 * @author penghui.li
 *
 */
public class DBUtil {

	final static String connStr = "jdbc:mysql://192.168.2.5:3306/fclub_report_dev?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
	final static String userStr = "fclub_report";
	final static String pwdStr = "Reslekdu_#@iw832";
	public static Connection connDB() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Connection con = null;
		con = DriverManager.getConnection(connStr,userStr,pwdStr);
		return con;
	}
	
}
