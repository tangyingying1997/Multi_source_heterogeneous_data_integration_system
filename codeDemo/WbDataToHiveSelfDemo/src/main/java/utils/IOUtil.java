package utils;
/**
 * �����ļ����ƣ���ȡ�����ļ��е�����
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
	 * �����ļ�������ȡ���ļ�����
	 * @param args
	 */
	public static List<String> getTxtContent(String txtFilePath,String charset)
		throws Exception {
		//1.�½�File�ඨλ�ļ�λ��
		File txtFile = new File(txtFilePath);
		//2.�ļ�������
		FileInputStream fis = new FileInputStream(txtFile);
		//3.�ַ�������
		InputStreamReader isr = new InputStreamReader(fis,charset);
		//4.�ֽ���������
		BufferedReader br = new BufferedReader(isr);
		
		//5.��������
		List<String> lineList = new ArrayList<String>();
		
		//6.��
		String tempLine = null;
		while((tempLine = br.readLine()) != null){
			lineList.add(tempLine);
		}
		//7.����
		br.close();
		
		//8.����
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
		String txtFilePath = "房地产\\user\\1107717945.txt";
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
