package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把字符串转为日期类型
 * @author Apollo
 *
 */
public class DateUtil {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//字符串类型的日期转为Date类型的日期
	public static Date getDate(String dateString) throws ParseException{
		return dateFormat.parse(dateString);
	}
	//不规范的日期转为年月日形式的日期
	public static String formatDate(Date date){
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) throws ParseException{
		//System.out.println(formatDate(2012-4-6 4:45:52));
		System.out.println(getDate("2012-4-6 5:45:52"));
		System.out.println(formatDate(getDate("2012-4-6 5:45:52")));
	}
}
