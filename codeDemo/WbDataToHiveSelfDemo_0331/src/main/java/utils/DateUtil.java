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
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static Date getDate(String dateString) throws ParseException{
		return dateFormat.parse(dateString);
	}
}
