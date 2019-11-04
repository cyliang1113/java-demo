package cn.leo.java.demo.xml.dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo01 {
	static{
		System.out.println("static block.");
	}
	
	
	public static void main(String[] args) throws DocumentException {
		
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(Demo01.class.getClassLoader().getResourceAsStream("cn/leo/java/demo/xml/file/books.xml"));
		String encoding = document.getXMLEncoding();
		System.out.println(encoding);
		
		Element root = document.getRootElement();
		
		List<Element> elements = root.elements();
		for (Element e : elements) {
			System.out.println("element name: " + e.getName());
			System.out.println("element NamespacePrefix: " + e.getNamespacePrefix());
			System.out.println("element NamespaceURI: " + e.getNamespaceURI());
		}

	}
}
