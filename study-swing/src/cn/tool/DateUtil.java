package cn.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String stringToDate(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
		
	}
}
