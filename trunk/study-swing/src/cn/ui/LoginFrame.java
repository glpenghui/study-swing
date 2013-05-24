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
	JFrame jf = new JFrame("�������˵�½");
	JPanel jp = new JPanel();
	JTable table;      //�����ά������Ϊ�������      
	JLabel userName = new JLabel("�û��� :"); 
	JLabel password = new JLabel("��    �� :");
	static JTextField userNameIn = new JTextField(12);
	static JPasswordField passwordIn = new JPasswordField(12);
	JTabbedPane tp = new JTabbedPane();
	
	JButton submit = new JButton("��½");
	JButton cancel = new JButton("ȡ��");
	
	
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
		
		tp.add("�û���¼",jp);
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
		
		//ע���¼�
		submitClick();
		
		System.out.println("===============��½���ڳ�ʼ���ɹ���");
	}
	
	
	//��½ע��ʱ��
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
			JOptionPane.showMessageDialog(null,"�û�������Ϊ�գ�", "��ʾ",JOptionPane.ERROR_MESSAGE);
			int response = JOptionUtil.open("����  DD2013031118866 \n ����Ϊ��12.333kg!", new String[]{"ȷ��","ȡ������"},"ȷ��");
			userNameIn.requestFocus();
			return;
		}
        
        if(password.equals("")){
        	VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"���벻��Ϊ�գ�", "��ʾ",JOptionPane.ERROR_MESSAGE);
			
			passwordIn.requestFocus();
			return;
		}
		int result = new PreAction().login(adminName,password);
		if(result > 0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"��½�ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
		}
		if(result == -21){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"���û�û�ж������˵�Ȩ�ޣ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			passwordIn.setText("");
			userNameIn.requestFocus();
			return;
		}
		if(result == -31){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����������������룡", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			passwordIn.setText("");
			passwordIn.requestFocus();
			return;
		}
		if(result == -41){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"�û��������ڣ����������룡", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			userNameIn.requestFocus();
			return;
		}
		if(result == -51){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"δ֪��������ϵ������Ա��", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			userNameIn.setText("");
			passwordIn.setText("");
			return;
		}
		new MainFrame(result,adminName);
		jf.dispose();
	}
}
