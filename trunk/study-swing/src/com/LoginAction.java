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
			System.out.println("===============���ݿ����ӳɹ���");
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()){
				JOptionPane.showMessageDialog(null,"���û������ڻ����������","error", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "��½�ɹ���", "Next",JOptionPane.YES_OPTION);
				System.out.println("===============��½�ɹ���");
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
