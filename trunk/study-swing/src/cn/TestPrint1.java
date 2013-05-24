package cn;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFrame;

import com.hg.jpd.JpdViewer;

public class TestPrint1 {
	 
		    /**  
		     * JPD套打测试  
		     * @param args  
		     */  
		    public static void main(String[] args) {   
		        try {   
//		            JFrame f = new JFrame("JPD套打测试");   
//		            Container p = f.getContentPane();   
		            //实例化JPD阅读器   
		            JpdViewer viewer = new JpdViewer();   
		            //指定url打开文件   
		            viewer.open("fclub-no.jpd");   
//		            
		            //设置数据   
		            viewer.setItemValue("标题", "中国聚尚借款凭借单"); 
		            viewer.setItemValue("资金性质", "支票");   
		            viewer.setItemValue("年1", "2012");   
		            viewer.setItemValue("月1", "5");   
		            viewer.setItemValue("日1", "17");   
		            viewer.setItemValue("借款单位", "技术部");   
		            viewer.setItemValue("借款理由", "部门经费");   
		            viewer.setItemValue("借款数额", "贰万元整");   
		            viewer.setItemValue("数额", "20000");   
		            viewer.setItemValue("意见", "测试意见");   
		            viewer.setItemValue("签章", "牛老大");   
		            viewer.setItemValue("批示", "李朋辉");   
		            viewer.setItemValue("核批", "李朋辉");   
		            viewer.setItemValue("年2", "2012");   
		            viewer.setItemValue("月2", "5");   
		            viewer.setItemValue("日2", "18"); 
		            viewer.setItemValue("号", "520");
//		            viewer.set
		            viewer.print();
//		            
//		            
//		            HashPrintRequestAttributeSet hashprintrequestattributeset = new HashPrintRequestAttributeSet();
//		            javax.print.DocFlavor.SERVICE_FORMATTED service_formatted = javax.print.DocFlavor.SERVICE_FORMATTED.PRINTABLE;
//		            PrintService aprintservice[] = PrintServiceLookup.lookupPrintServices(service_formatted, hashprintrequestattributeset);
//		            PrintService printservice = PrintServiceLookup.lookupDefaultPrintService();
//		            
		            
		            //加入到面板中   
//		            p.add(viewer, BorderLayout.CENTER);  
//		            
//		            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
//		            f.setSize(800, 600);   
//		            f.setVisible(true);   
//		            f.setExtendedState(JFrame.MAXIMIZED_BOTH);   
		        } catch (Exception e) {   
		            e.printStackTrace();   
		        }   
		    }   
		}  


