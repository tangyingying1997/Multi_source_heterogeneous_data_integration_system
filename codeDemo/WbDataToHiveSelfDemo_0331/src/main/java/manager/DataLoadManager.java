package manager;

import java.util.ArrayList;
import java.util.List;

import pojos.WbContentInfoPojo;
import pojos.WbUserInfoPojo;
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
		
		//对读出的内容集res做结构化
		//1.定义user集
		List<WbUserInfoPojo> userPojoList = new ArrayList<>();
		//2.定义content集
		List<WbContentInfoPojo> contentPojoList = new ArrayList<>();
		
		//遍历res的每一行
		for(String line : res){
			//首先去掉前后的空格
			line = line.trim();
			
			if(line.length() == 0){
				continue;
			}
			//如果以 < 开头，说明是content
			if(line.startsWith("<")){
				
			}else{
				//否则就是user，暂时不考虑其他类型的数据
				//先把[...]之间的内容截取出来
				line = (String) line.subSequence(line.indexOf('[')+1, line.lastIndexOf(']'));
				//再按照逗号切割
				String[] kvArray = line.split(",");
				
				WbUserInfoPojo userInfoPojo = new WbUserInfoPojo();
				
				//遍历每个kvArray，找每个字段对应的值
				for(String kv : kvArray){
					kv = kv.trim();  //很重要，否则会有空指针
					//System.out.println(kv);
					//按照等号把字段和值分割开
					String[] kvPair = kv.split("=");
					if(kvPair[0] .equals("id")){
						userInfoPojo.setId(Long.parseLong(kvPair[1]));
					}else if(kvPair[0] .equals("screenName")){
						userInfoPojo.setScreenName(kvPair[1]);
					}else if(kvPair[0] .equals("name")){
						userInfoPojo.setName(kvPair[1]);
					}else if(kvPair[0] .equals("province")){
						userInfoPojo.setProvince(Integer.parseInt(kvPair[1]));
					}else if(kvPair[0] .equals("remark")){
						userInfoPojo.setRemark(kvPair[1]);
					}
				}
				System.out.println(userInfoPojo.toString4FileOutput());
				//System.out.println(line);
			}
			
		}
	}
}
