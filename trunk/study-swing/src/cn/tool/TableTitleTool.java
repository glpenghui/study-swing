package cn.tool;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

public class TableTitleTool {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getCheckOrderTableTitle(){
		Vector list2 = new Vector();
		list2.addElement("����");
		list2.addElement("��Ʒ���");
		list2.addElement("����");
		list2.addElement("Ʒ��");
		list2.addElement("����");
		list2.addElement("��ɫ");
		list2.addElement("����");
		list2.addElement("��������");
		list2.addElement("��������");
		list2.addElement("δ��������");
		list2.addElement("״̬");
		list2.addElement("subId");
		list2.addElement("�����");
		return list2;
	}
	
	/**
	* ����JTable�в���Ҫ��ʾ����
	* @param table ��Ҫ�����е�JTable
	* @param colIndex ��Ҫ���ص��е��±꣨JTable���±��0��ʼ��
	*/
	public static void hide(JTable table, int[] colIndex) {
	   DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
	   for (int i = 0; i < colIndex.length; i++) {
	    dcm.getColumn(colIndex[i]).setPreferredWidth(0);
	    dcm.getColumn(colIndex[i]).setMinWidth(0);
	    dcm.getColumn(colIndex[i]).setMaxWidth(0);
	   }

	}

}


