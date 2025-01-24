package util;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

public class XMLParser {

	// XML 파싱 문자열을 Document 객체로 변환
    public static Document parseXML(String xml) throws Exception {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
        return (Document) builder.parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));
    }
    
    // 특정 태그 이름으로 NodeList 추출 
    public static NodeList getNodeListByTagName(Document doc, String tagName) {
        return doc.getElementsByTagName(tagName);
    }


}

