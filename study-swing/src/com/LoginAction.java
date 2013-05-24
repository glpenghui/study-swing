package com;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginAction {
 
	public void login(String name,String passwd){
		String pwd = String.valueOf(passwd);
		String sql = "select 1 from dual";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conn = DBUtil.connDB();
			System.out.println("===============数据库连接成功！");
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()){
				JOptionPane.showMessageDialog(null,"此用户不存在或者密码错误","error", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "登陆成功！", "Next",JOptionPane.YES_OPTION);
				System.out.println("===============登陆成功！");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
}
