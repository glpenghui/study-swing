package cn.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.data.PreAction;
import cn.tool.JOptionUtil;
import cn.tool.VoiceUtil;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame{
	JFrame jf = new JFrame("订单复核登陆");
	JPanel jp = new JPanel();
	JTable table;      //定义二维数组作为表格数据      
	JLabel userName = new JLabel("用户名 :"); 
	JLabel password = new JLabel("密    码 :");
	static JTextField userNameIn = new JTextField(12);
	static JPasswordField passwordIn = new JPasswordField(12);
	JTabbedPane tp = new JTabbedPane();
	
	JButton submit = new JButton("登陆");
	JButton cancel = new JButton("取消");
	
	
	public LoginFrame(){
		userName.setBounds(50,60,50,25);
		userNameIn.setBounds(105,60,150,25);
		password.setBounds(50,100,50,25);
		passwordIn.setBounds(105,100,150,25);
		submit.setBounds(80,155,60,25);
		cancel.setBounds(160,155,60,25);
		
		jp.add(userName);jp.add(userNameIn);
		jp.add(password);jp.add(passwordIn);
		jp.add(submit);
		jp.add(cancel);
		
		jp.setSize(250,300);
		
		tp.add("用户登录",jp);
		tp.setSize(250, 300);
		
		jf.add(tp, BorderLayout.CENTER);
		
		jp.setLayout(null);
		
		jf.pack();          
		jf.setVisible(true);
		jf.setResizable(true);

		jf.setResizable(false);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setSize(300, 400);
		jf.setLocationRelativeTo(getOwner()); 
		
		//注册事件
		submitClick();
		
		System.out.println("===============登陆窗口初始化成功！");
	}
	
	
	//登陆注册时间
	public void submitClick(){
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				login();
		}}); 
	}
	
	public void login(){
		String adminName = userNameIn.getText();
		String password = passwordIn.getText();
		
		if(adminName.equals("")){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"用户名不能为空！", "提示",JOptionPane.ERROR_MESSAGE);
			int response = JOptionUtil.open("订单  DD2013031118866 \n 重量为：12.333kg!", new String[]{"确定","取消复核"},"确定");
			userNameIn.requestFocus();
			return;
		}
        
        if(password.equals("")){
        	VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"密码不能为空！", "提示",JOptionPane.ERROR_MESSAGE);
			
			passwordIn.requestFocus();
			return;
		}
		int result = new PreAction().login(adminName,password);
		if(result > 0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"登陆成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
		}
		if(result == -21){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"该用户没有订单复核的权限！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			passwordIn.setText("");
			userNameIn.requestFocus();
			return;
		}
		if(result == -31){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"密码错误，请重新输入！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			passwordIn.setText("");
			passwordIn.requestFocus();
			return;
		}
		if(result == -41){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"用户名不存在，请重新输入！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			userNameIn.requestFocus();
			return;
		}
		if(result == -51){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"未知错误，请联系技术人员！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			passwordIn.setText("");
			return;
		}
		new MainFrame(result,adminName);
		jf.dispose();
	}
}
