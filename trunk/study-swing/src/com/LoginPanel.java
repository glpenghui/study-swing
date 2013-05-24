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
 * ��½����
 * @author penghui.li
 *
 */
public class LoginPanel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame jf = new JFrame("��½����");
	JPanel jp = new JPanel();
	
	JLabel nameT = new JLabel("������"); 
	JLabel passwdT = new JLabel("���룺");
	
	static JTextField nameIn = new JTextField(10);
	static JTextField passwdIn = new JTextField(10);
	
	JButton submit = new JButton("��½");
	JButton cancel = new JButton("ȡ��");
	
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
		System.out.println("===============���ڳ�ʼ���ɹ���");
	}

	
	public static void check(){
		if(nameIn.getText().equals("")){
			JOptionPane.showMessageDialog(null,"�������û�����", "error",JOptionPane.ERROR_MESSAGE);
		}else if(passwdIn.getText().equals("")){
			JOptionPane.showMessageDialog(null,"����������","error", JOptionPane.ERROR_MESSAGE);
		}
		
		new LoginAction().login(nameIn.getText(), passwdIn.getText());
	}
}
