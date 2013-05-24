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
		   JTextArea textArea = new JTextArea("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
		   //通过设置textArea的列和行来设置对话框显示内容的大小
		   textArea.setColumns(20);
		   textArea.setRows(5);
		   Font f = new Font(null, 0, 32);
		   Color cr = new Color(255,0,0);
		   textArea.setForeground(cr);
		   textArea.setBackground(null);
		   textArea.setFont(f);
		   textArea.setLineWrap(true);//超过设置的列数自动换行
		   textArea.setEditable(false);//设置不可编辑
//		   JOptionPane.showMessageDialog(null, new JScrollPane(textArea));//加入滚动条
		   int response=JOptionPane.showOptionDialog(null, new JScrollPane(textArea), "提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"确定","取消复核"}, "确定");
//		   JOptionPane.showMessageDialog(null,  "出现未知错误，请联系技术人员！","提示",JOptionPane.INFORMATION_MESSAGE);
	}
}
