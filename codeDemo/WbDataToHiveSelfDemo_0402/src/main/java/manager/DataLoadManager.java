package manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import pojos.UserAndContentInfoPojo;
import pojos.WbContentInfoPojo;
import pojos.WbUserInfoPojo;
import utils.DateUtil;
import utils.FileOperatorUtil;
import utils.IOUtil;
import utils.XmlParserUtil;

public class DataLoadManager {

	/**
	 * 为了组装content的uid，创建一个内部类
	 * 
	 * @author Apollo
	 *
	 */
	static class UidAndListPojo {
		private String uid;
		private List<String> lineList;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public List<String> getLineList() {
			return lineList;
		}

		public void setLineList(List<String> lineList) {
			this.lineList = lineList;
		}

		@Override
		public String toString() {
			return "UidAndListPojo [uid=" + uid + ", lineList=" + lineList + "]";
		}
		
	}

	/**
	 * 将指定目录里的文本文件读取成List
	 * 
	 * @param inputDir
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static List<UidAndListPojo> getAllFileLineList(String inputDir, String charset) throws Exception {
		// 结果集
		ArrayList<UidAndListPojo> uidAndListPojoList = new ArrayList<>();
		// 遍历
		List<String> txtFilePathList = FileOperatorUtil.getAllSubNormalFilePath(inputDir);
		for (String txtFilePath : txtFilePathList) {
			ArrayList<String> txtLineList = new ArrayList<>();
			List<String> singleTxtLineList = IOUtil.getTxtContent(txtFilePath, charset);
			txtLineList.addAll(singleTxtLineList);
			String uidValue = FileOperatorUtil.getFileNameWithoutSuffix(txtFilePath);
			UidAndListPojo uidAndListPojo = new UidAndListPojo();
			uidAndListPojo.setUid(uidValue);
			uidAndListPojo.setLineList(txtLineList);
			
			// 把uid+list的大pojo加进去
			uidAndListPojoList.add(uidAndListPojo);
		}
		return uidAndListPojoList;
	}

	/**
	 * 将字符串的list结构化成对象pojo，并整合成一个pojo的list
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws Exception
	 */
	public static UserAndContentInfoPojo getConstructInfoPojo(List<UidAndListPojo> uidAndListPojoList)
			throws ParseException {
		// 对读出的内容集res做结构化
		// 1.定义user集
		List<WbUserInfoPojo> userPojoList = new ArrayList<>();
		// 2.定义content集
		List<WbContentInfoPojo> contentPojoList = new ArrayList<>();
		// 定义一个错误行数计数器
		int errorLineCounter = 0;

		// 遍历uidAndListPojoList的每一行
		for (UidAndListPojo uidAndListPojo : uidAndListPojoList) {
			String uidValue = uidAndListPojo.getUid();
			for (String line : uidAndListPojo.getLineList()) {
				// 首先去掉前后的空格
				line = line.trim();
				if (line.length() == 0) {
					continue;
				}
				// 如果以 < 开头，说明是content
				if (line.startsWith("<")) {
					// TODO
					line = line.trim();
					Element rootElement = XmlParserUtil.getXmlRootElement(line);
					/**
					 * 加一个错误计数
					 */
					if(rootElement == null){
						System.out.println("xml解析数显错误");
						errorLineCounter++;
						continue;
					}
					
					WbContentInfoPojo contentInfoPojo = new WbContentInfoPojo();
					// contentInfoPojo.setId(Long.parseLong(rootElement.elementText("id")));
					contentInfoPojo.setId(Long.parseLong(uidValue));
					contentInfoPojo.setContent(rootElement.elementText("content"));
			
					//
					//System.out.println("----rootElement.elementText-----"+rootElement.elementText("time"));
					String nodeTime = rootElement.elementText("time");
					//System.out.println("---nodeTime:---"+nodeTime);
					Date string_to_date_time = DateUtil.getDate(nodeTime);
					//System.out.println("---string_to_date_time:---"+string_to_date_time);
					//String date_time_to_format = DateUtil.formatDate(string_to_date_time);
					//System.out.println("---date_time_to_format:---"+date_time_to_format);
					//Date final_time = DateUtil.getDate(date_time_to_format);
					//System.out.println("---final_time:---"+final_time);
					contentInfoPojo.setTime(string_to_date_time);
					//
					
					//把set进去的time拿出来看一下
					/*System.out.println("---time----");
					System.out.println(contentInfoPojo.getTime());*/
					//
					
					contentInfoPojo.setRepostsCount(Integer.parseInt(rootElement.elementText("repostsCount")));
					contentInfoPojo.setCommentsCount(Integer.parseInt(rootElement.elementText("commentsCount")));

					// 造好一个content就丢进contentlist
					contentPojoList.add(contentInfoPojo);
				} else {
					// 否则就是user，暂时不考虑其他类型的数据
					// 先把[...]之间的内容截取出来
					line = (String) line.subSequence(line.indexOf('[') + 1, line.lastIndexOf(']'));
					// 再按照逗号切割
					String[] kvArray = line.split(",");

					WbUserInfoPojo userInfoPojo = new WbUserInfoPojo();

					// 遍历每个kvArray，找每个字段对应的值
					for (String kv : kvArray) {
						kv = kv.trim(); // 很重要，否则会有空指针
						// System.out.println(kv);
						// 按照等号把字段和值分割开
						String[] kvPair = kv.split("=");
						if (kvPair[0].equals("id")) {
							userInfoPojo.setId(Long.parseLong(kvPair[1]));
						} else if (kvPair[0].equals("screenName")) {
							userInfoPojo.setScreenName(kvPair[1]);
						} else if (kvPair[0].equals("name")) {
							userInfoPojo.setName(kvPair[1]);
						} else if (kvPair[0].equals("province")) {
							userInfoPojo.setProvince(Integer.parseInt(kvPair[1]));
						} else if (kvPair[0].equals("remark")) {
							userInfoPojo.setRemark(kvPair[1]);
						}
					}
					// 测试下生成的UserPojo的值写进去了没有
					// System.out.println(userInfoPojo.toString4FileOutput());
					// System.out.println(line);
					// 成功set号一个UserPoJO之后要记得，塞进list里
					userPojoList.add(userInfoPojo);
				}
			}
		}
			return new UserAndContentInfoPojo(userPojoList, contentPojoList);
	}


	/**
	 * 传入复合pojo，将复合pojo拆分并且分别持久化到对应的txt文件中
	 * 
	 * @param userAndContentInfoPojo
	 * @param userOutputFilePath
	 * @param contentOutputFilePath
	 * @param outputCharset
	 * @return
	 * @throws Exception
	 */
	public static boolean writePojoToFile(UserAndContentInfoPojo userAndContentInfoPojo, String userOutputFilePath,
			String contentOutputFilePath, String outputCharset) throws Exception {
		// 1.把userPojoList序列化
		List<WbUserInfoPojo> userInfoPojoList = userAndContentInfoPojo.getUserPojoList();
		StringBuilder stringBuilder = new StringBuilder();
		int lineCounter = 0;
		for (WbUserInfoPojo tempPojo : userInfoPojoList) {
			if (lineCounter > 0) {
				stringBuilder.append("\n");
			}
			stringBuilder.append(tempPojo.toString4FileOutput());
			lineCounter++;
		}
		IOUtil.writeListToFile(stringBuilder.toString(), userOutputFilePath, outputCharset);
		
		// 2.序列化contentPojoList
		List<WbContentInfoPojo> contentInfoPojoList = userAndContentInfoPojo.getContentPojoList();
		StringBuilder stringBuilder2 = new StringBuilder();
		int lineCounter2 = 0;
		for (WbContentInfoPojo tempPojo : contentInfoPojoList) {
			if (lineCounter2 > 0) {
				stringBuilder2.append("\n");
			}
			stringBuilder2.append(tempPojo.toString4FileOutput());
			lineCounter2++;
		}
		// 3.调用序列化方法
		IOUtil.writeListToFile(stringBuilder2.toString(), contentOutputFilePath, outputCharset);
		return true;
	}

	public static void main(String[] args) throws Exception {
		String inputDir = "房地产";
		String inputCharset = "utf-8";
		String userOutputFilePath = "user_pojo_list.txt";
		String contentOutputFilePath = "content_pojo_list.txt";
		String outputCharset = "utf-8";
		// 1.把给定目录中的文本文件读取成list
		List<UidAndListPojo> uidAndListPojoList = getAllFileLineList(inputDir, inputCharset);
		
		// 2.将字符串的list转换成结构化的pojolist
		UserAndContentInfoPojo userAndContentInfoPojo = getConstructInfoPojo(uidAndListPojoList);
		 //System.out.println(userAndContentInfoPojo.toString());
		
		writePojoToFile(userAndContentInfoPojo, userOutputFilePath, contentOutputFilePath, outputCharset);
		System.out.println("DONE");
	}
}
