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
		     * JPD�״����  
		     * @param args  
		     */  
		    public static void main(String[] args) {   
		        try {   
//		            JFrame f = new JFrame("JPD�״����");   
//		            Container p = f.getContentPane();   
		            //ʵ����JPD�Ķ���   
		            JpdViewer viewer = new JpdViewer();   
		            //ָ��url���ļ�   
		            viewer.open("fclub-no.jpd");   
//		            
		            //��������   
		            viewer.setItemValue("����", "�й����н��ƾ�赥"); 
		            viewer.setItemValue("�ʽ�����", "֧Ʊ");   
		            viewer.setItemValue("��1", "2012");   
		            viewer.setItemValue("��1", "5");   
		            viewer.setItemValue("��1", "17");   
		            viewer.setItemValue("��λ", "������");   
		            viewer.setItemValue("�������", "���ž���");   
		            viewer.setItemValue("�������", "����Ԫ��");   
		            viewer.setItemValue("����", "20000");   
		            viewer.setItemValue("���", "�������");   
		            viewer.setItemValue("ǩ��", "ţ�ϴ�");   
		            viewer.setItemValue("��ʾ", "�����");   
		            viewer.setItemValue("����", "�����");   
		            viewer.setItemValue("��2", "2012");   
		            viewer.setItemValue("��2", "5");   
		            viewer.setItemValue("��2", "18"); 
		            viewer.setItemValue("��", "520");
//		            viewer.set
		            viewer.print();
//		            
//		            
//		            HashPrintRequestAttributeSet hashprintrequestattributeset = new HashPrintRequestAttributeSet();
//		            javax.print.DocFlavor.SERVICE_FORMATTED service_formatted = javax.print.DocFlavor.SERVICE_FORMATTED.PRINTABLE;
//		            PrintService aprintservice[] = PrintServiceLookup.lookupPrintServices(service_formatted, hashprintrequestattributeset);
//		            PrintService printservice = PrintServiceLookup.lookupDefaultPrintService();
//		            
		            
		            //���뵽�����   
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


