package cn.tool;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JOptionUtil {

	public static int open(String message,String [] param,String ok){
		 JTextArea textArea = new JTextArea(message);
		   //ͨ������textArea���к��������öԻ�����ʾ���ݵĴ�С
		   textArea.setColumns(20);
		   textArea.setRows(5);
		   Font f = new Font(null, 0, 32);
		   Color cr = new Color(255,0,0);
		   textArea.setForeground(cr);
		   textArea.setBackground(null);
		   textArea.setFont(f);
		   textArea.setLineWrap(true);//�������õ������Զ�����
		   textArea.setEditable(false);//���ò��ɱ༭
//		   JOptionPane.showMessageDialog(null, new JScrollPane(textArea));//���������
		   int response=JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "��ʾ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, param, ok);
//		   JOptionPane.showMessageDialog(null,  "����δ֪��������ϵ������Ա��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		   return response;
	}
}
