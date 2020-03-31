package utils;

import java.util.List;

public class StringUtil {
	public static String join(List<Object> objList,String deli){
		int lineCounter = 0;
		StringBuilder stringBuilder = new StringBuilder();
		for(Object obj : objList){
			if(lineCounter > 0){
				stringBuilder.append(deli);
			}
			stringBuilder.append(obj.toString());
			lineCounter++;
		}
		return stringBuilder.toString();
	}
}
