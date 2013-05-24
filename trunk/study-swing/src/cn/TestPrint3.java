package cn;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestPrint3 {

	public static void main(String [] args) {
		try {			//file... E:\desktop\Desktop\Ë³·áÄ£°å\my sf\report2.jasper
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("e:/desktop/Desktop/Ë³·áÄ£°å/my sf/report2.jasper");
			Map parameters = new HashMap();
			parameters.put("parameter1","aaaa");
			parameters.put("parameter2","bbbb");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
			JasperPrintManager.printReport(jasperPrint, false);  
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
