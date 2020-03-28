package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 指定文件夹，找出文件夹中的文件
 * @author Apollo
 *
 */
public class FileOperatorUtil {
	/**
	 * 根据路径，找出其下的所有文件
	 */
	public static List<String> getAllSubNormalFilePath(String filePath){
		//1.创建文件类，定位到该路径
		File file = new File(filePath);
		//2.创建List用来存储遍历出的结果集
		List<String> resultList = new ArrayList();
		//3.判断是否是目录
		if(file.isDirectory()){
			//列出该目录下的全部文件
			for(File tempFile : file.listFiles()){
				resultList.addAll(getAllSubNormalFilePath(tempFile.toString()));
			}
		}else{
			//如果是文件，直接加进list
			resultList.add(file.toString());
		}
		//4.返回结果集
		return resultList;
	}
	
	/**
	 * 编写测试方法
	 */
	public static void main(String[] args){
		String filePath = "房地产";
		List<String> res = getAllSubNormalFilePath(filePath);
		//遍历打印
		System.out.println("res的个数为："+res.size());
		System.out.println("------------------------");
		for(String temp : res){
			System.out.println(temp);
		}
		
	}
}
