//package cn.tool;
//
//import java.util.Locale;
//
//import javax.print.DocPrintJob;
//import javax.print.PrintService;
//import javax.print.PrintServiceLookup;
//import javax.print.ServiceUI;
//import javax.print.SimpleDoc;
//import javax.print.attribute.HashDocAttributeSet;
//import javax.print.attribute.HashPrintRequestAttributeSet;
//import javax.print.attribute.standard.JobName;
//import javax.print.attribute.standard.MediaPrintableArea;
//import javax.print.attribute.standard.MediaSize;
//import javax.print.attribute.standard.OrientationRequested;
//
//import com.hg.jpd.JpdViewer;
//import com.hg.jpd.b;
//
//public class MyJpdViewer extends JpdViewer{
//	@Override
////	 public void print()
//	    {
//	        if(!validateItems())
//	            return;
//	        HashPrintRequestAttributeSet hashprintrequestattributeset = new HashPrintRequestAttributeSet();
//	        javax.print.DocFlavor.SERVICE_FORMATTED service_formatted = javax.print.DocFlavor.SERVICE_FORMATTED.PRINTABLE;
//	        PrintService aprintservice[] = PrintServiceLookup.lookupPrintServices(service_formatted, hashprintrequestattributeset);
//	        PrintService printservice = PrintServiceLookup.lookupDefaultPrintService();
//	        if(printservice != null)
//	        {
//	            hashprintrequestattributeset.add(new JobName(c.n, Locale.getDefault()));
//	            javax.print.attribute.standard.MediaSizeName mediasizename = MediaSize.findMedia((float)c.m / 72F, (float)c._fldbyte / 72F, 25400);
//	            if(mediasizename != null)
//	                hashprintrequestattributeset.add(mediasizename);
//	            if(c.m > c._fldbyte)
//	            {
//	                hashprintrequestattributeset.add(OrientationRequested.LANDSCAPE);
//	                hashprintrequestattributeset.add(new MediaPrintableArea(0.0F, 0.0F, (float)c._fldbyte / 72F, (float)c.m / 72F, 25400));
//	            } else
//	            {
//	                hashprintrequestattributeset.add(OrientationRequested.PORTRAIT);
//	                hashprintrequestattributeset.add(new MediaPrintableArea(0.0F, 0.0F, (float)c.m / 72F, (float)c._fldbyte / 72F, 25400));
//	            }
//	            PrintService printservice1 = ServiceUI.printDialog(null, 200, 200, aprintservice, printservice, service_formatted, hashprintrequestattributeset);
//	            if(printservice1 != null)
//	                try
//	                {
//	                    DocPrintJob docprintjob = printservice1.createPrintJob();
//	                    HashDocAttributeSet hashdocattributeset = new HashDocAttributeSet();
//	                    SimpleDoc simpledoc = new SimpleDoc(c, service_formatted, hashdocattributeset);
//	                    docprintjob.print(simpledoc, hashprintrequestattributeset);
//	                }
//	                catch(Exception exception)
//	                {
//	                    a(exception);
//	                }
//	        }
//	    }
//}
