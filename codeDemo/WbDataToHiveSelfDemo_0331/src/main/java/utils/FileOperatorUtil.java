package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ָ���ļ��У��ҳ��ļ����е��ļ�
 * @author Apollo
 *
 */
public class FileOperatorUtil {
	/**
	 * ����·�����ҳ����µ������ļ�
	 */
	public static List<String> getAllSubNormalFilePath(String filePath){
		//1.�����ļ��࣬��λ����·��
		File file = new File(filePath);
		//2.����List�����洢�������Ľ����
		List<String> resultList = new ArrayList();
		//3.�ж��Ƿ���Ŀ¼
		if(file.isDirectory()){
			//�г���Ŀ¼�µ�ȫ���ļ�
			for(File tempFile : file.listFiles()){
				resultList.addAll(getAllSubNormalFilePath(tempFile.toString()));
			}
		}else{
			//������ļ���ֱ�Ӽӽ�list
			resultList.add(file.toString());
		}
		//4.���ؽ����
		return resultList;
	}
	
	/**
	 * ��д���Է���
	 */
	public static void main(String[] args){
		String filePath = "房地产";
		List<String> res = getAllSubNormalFilePath(filePath);
		//������ӡ
		System.out.println("res的大小"+res.size());
		System.out.println("------------------------");
		for(String temp : res){
			System.out.println(temp);
		}
		
	}
}
