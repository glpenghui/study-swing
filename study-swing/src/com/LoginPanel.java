package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 登陆窗体
 * @author penghui.li
 *
 */
public class LoginPanel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame jf = new JFrame("登陆窗口");
	JPanel jp = new JPanel();
	
	JLabel nameT = new JLabel("姓名："); 
	JLabel passwdT = new JLabel("密码：");
	
	static JTextField nameIn = new JTextField(10);
	static JTextField passwdIn = new JTextField(10);
	
	JButton submit = new JButton("登陆");
	JButton cancel = new JButton("取消");
	
	public LoginPanel(){
		jp.add(nameT);jp.add(nameIn);
		jp.add(passwdT);jp.add(passwdIn);
		jp.add(submit);jp.add(cancel);
		
		jf.add(jp);
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				check();
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		jf.setVisible(true);
		jf.setSize(200,150);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocation(500, 270);
		System.out.println("===============窗口初始化成功！");
	}

	
	public static void check(){
		if(nameIn.getText().equals("")){
			JOptionPane.showMessageDialog(null,"请输入用户名！", "error",JOptionPane.ERROR_MESSAGE);
		}else if(passwdIn.getText().equals("")){
			JOptionPane.showMessageDialog(null,"请输入密码","error", JOptionPane.ERROR_MESSAGE);
		}
		
		new LoginAction().login(nameIn.getText(), passwdIn.getText());
	}
}
