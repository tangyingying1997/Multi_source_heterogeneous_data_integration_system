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
		//4.返回结果
		return resultList;
	}
	
	/**
	 * 传入路径，取出其中的uid
	 */
	public static String getFileNameWithoutSuffix(String inputPath){
		return new File(inputPath).getName().split("\\.")[0];
	}
	/**
	 * 测试
	 */
	public static void main(String[] args){
		String filePath = "房地产";
		List<String> res = getAllSubNormalFilePath(filePath);
		//测试大小
		System.out.println("res的大小"+res.size());
		System.out.println("------------------------");
		for(String temp : res){
			System.out.println(temp);
		}
		
	}
}
