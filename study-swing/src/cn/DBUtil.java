package cn;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库连接
 * @author penghui.li
 *
 */
public class DBUtil {

	final static String connStr = "jdbc:mysql://192.168.1.16:3306/baby?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
	final static String userStr = "web_baby";
	final static String pwdStr = "web_baby";
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
	
//	public static JdbcTemplate getJdbcTemplate(){
//		return null;
////		 JdbcTemplate baby_db = 
//	}
	
}
