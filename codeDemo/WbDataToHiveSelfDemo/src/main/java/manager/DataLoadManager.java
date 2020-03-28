package manager;

import java.util.ArrayList;
import java.util.List;

import utils.FileOperatorUtil;
import utils.IOUtil;

public class DataLoadManager {
	
	public static List<String> getAllFileLineList(String inputDir,String charset) throws Exception{
		ArrayList<String> resultList = new ArrayList<>();
		List<String> txtFilePathList = FileOperatorUtil.getAllSubNormalFilePath(inputDir);
		for(String txtFilePath : txtFilePathList){
			List<String> singleTxtLineList = IOUtil.getTxtContent(txtFilePath,charset);
			resultList.addAll(singleTxtLineList);
		}
		
		return resultList;
	}
	public static void main(String[] args) throws Exception{
		String inputDir = "房地产";
		String inputCharset = "utf-8";
		List<String> res = getAllFileLineList(inputDir,inputCharset);
		for(String line : res){
			System.out.println(line);
		}
	}
}
