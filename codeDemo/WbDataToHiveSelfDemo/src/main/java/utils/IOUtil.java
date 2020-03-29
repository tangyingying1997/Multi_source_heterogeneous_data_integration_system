package utils;
/**
 * 从指定文件中读出全部内容
 * @author Apollo
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {
	/**
	 * 从指定文件中读出全部内容
	 * @param args
	 */
	public static List<String> getTxtContent(String txtFilePath,String charset)
		throws Exception {
		//1.创建File对象定位文件路径
		File txtFile = new File(txtFilePath);
		//2.文件输入流
		FileInputStream fis = new FileInputStream(txtFile);
		//3.reader
		InputStreamReader isr = new InputStreamReader(fis,charset);
		//4.字符读取
		BufferedReader br = new BufferedReader(isr);
		
		//5.创建存储结果的list
		List<String> lineList = new ArrayList<String>();
		
		//6.读数据
		String tempLine = null;
		while((tempLine = br.readLine()) != null){
			lineList.add(tempLine);
		}
		//7.关流
		br.close();
		
		//8.返回结果
		return lineList;
	}
	
	/**
	 * 转文件编码
	 */
	public static boolean writeListToFile(List<String> lineList,String outputFilePath,String charset) throws Exception{
		File outputFile = new File(outputFilePath);
		FileOutputStream fos = new FileOutputStream(outputFile);
		int lineCounter = 0;
		for(String line : lineList){
			if(lineCounter > 0){
				fos.write('\n');
			}
			fos.write(line.getBytes(charset));
			lineCounter++;
		}
		fos.close();
		return true;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String txtFilePath = "房地产\\content\\1107717945.txt";
		String inputCharset = "utf-8";
		String outputCharset = "utf-8";
		String outputFilePath = "newFile.txt";
		List<String> res = getTxtContent(txtFilePath,inputCharset);
		for(String temp : res){
			System.out.println(temp);
		}
		writeListToFile(res,outputFilePath,outputCharset);
	}

}
