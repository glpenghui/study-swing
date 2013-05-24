package cn.tool;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

public class TableTitleTool {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getCheckOrderTableTitle(){
		Vector list2 = new Vector();
		list2.addElement("名称");
		list2.addElement("商品款号");
		list2.addElement("货号");
		list2.addElement("品牌");
		list2.addElement("条码");
		list2.addElement("颜色");
		list2.addElement("尺码");
		list2.addElement("分配数量");
		list2.addElement("复核数量");
		list2.addElement("未复核数量");
		list2.addElement("状态");
		list2.addElement("subId");
		list2.addElement("拣货号");
		return list2;
	}
	
	/**
	* 隐藏JTable中不需要显示的列
	* @param table 需要隐藏列的JTable
	* @param colIndex 需要隐藏的列的下标（JTable列下标从0开始）
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


