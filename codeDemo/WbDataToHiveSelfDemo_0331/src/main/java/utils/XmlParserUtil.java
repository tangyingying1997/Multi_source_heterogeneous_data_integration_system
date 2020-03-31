package utils;

import java.io.File;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用来解析xml文件
 * @author Apollo
 *
 */
public class XmlParserUtil {

	public static Element getXmlRootElement(String xmlContent){
		SAXReader reader = new SAXReader();
		try{
			StringReader stringReader = new StringReader(xmlContent);
			Document document = reader.read(stringReader);
			Element rootElement = document.getRootElement();
			System.out.println(rootElement.elementText("content"));
			return rootElement;
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		String xmlContent = "<comment><content>回复@雷亚雷：在哈佛或MIT，每天都有各种议题的小型研讨会，凡在中午或晚餐时间举行的，一般都会准备晚餐。即使如此，有时也会忽略。//@雷亚雷：王老师，不吃午饭吗？</content><time>2012-4-6 5:45:52</time><repostsCount>574</repostsCount><commentsCount>290</commentsCount></comment>";
		Element e = getXmlRootElement(xmlContent);
		System.out.println(e.elementText("time"));
	}
}
