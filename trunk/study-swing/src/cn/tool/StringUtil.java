package cn.tool;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StringUtil {

	public static boolean isEmpty(String tm){
		if(tm==null || "".equals(tm))
			return true;
		return false;
	}
	
	public static String objectToString(Object o){
		if(o == null)return "";
		else return o.toString();
	}
	
	public static boolean isEmpty(Integer tm){
		if(tm==null || 0==tm)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		   JTextArea textArea = new JTextArea("���Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ���");
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
		   int response=JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "��ʾ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ȷ��","ȡ������"}, "ȷ��");
//		   JOptionPane.showMessageDialog(null,  "����δ֪��������ϵ������Ա��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
}
