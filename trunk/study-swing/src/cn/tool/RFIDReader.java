package cn.tool;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

public class RFIDReader implements SerialPortEventListener {
	static CommPortIdentifier portId;
	static Enumeration portList;
	InputStream inputStream;
	SerialPort serialPort;
	Thread readThread;
	public static  String weight = "";
	int mark = 0;
	Map<String,Integer> map = new HashMap<String,Integer>();
	 BufferedReader bf = null;
//	public static void main(String[] args) {
//		init();
//	}

	public static void init() {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM1")) {
					RFIDReader a = new RFIDReader();
					System.out.println("COM1 start!");
				}
			}
		}
		
		
	}

	public  RFIDReader() {
		try {
			serialPort = (SerialPort) portId.open("Sunder", 2000);
		} catch (PortInUseException e) {
			System.out.println(e);
		}
		try {
			
			inputStream = serialPort.getInputStream();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
			System.out.println(e);
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			System.out.println(e);
		}
	}


	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			try {
//				if(mark<10){
				 bf = new BufferedReader(new InputStreamReader(inputStream));
				 String str = bf.readLine();
				 if(str.length()==16){
//					 if(map.get(str) == null || map.get(str)==0){
//						 map.put(str, 1);
//					 }else{
//						 map.put(str, map.get(str)+1);
//					 }
//					 mark ++;
					 str = str.trim().substring(7).trim();
					 synchronized(weight) {
						 weight = (str==null || "".equals(str))?"0.000kg":str;
					 }
					 //System.out.println(str+"--"+str.length());
//					 Thread.sleep(100);
				  }
//				 }else{
//				  serialPort.close();
//				  inputStream.close();
//				  bf.close();
//				 }
				  
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		}
	}

}