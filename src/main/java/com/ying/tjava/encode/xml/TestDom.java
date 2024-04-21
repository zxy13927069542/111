package com.ying.tjava.encode.xml;

import java.io.*;

import javax.xml.parsers.*;

import org.junit.jupiter.api.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Dom: Document object model 一次性读取XML，并在内存中表示为树形结构
 * 不推荐使用，建议使用jackson-dataformat-xml
 */
public class TestDom {

	@Test
	public void testDom() throws ParserConfigurationException, SAXException, IOException {
		// 前面加 / 表示 target/classes/ 文件夹根路径，不加则是class文件所在路径
		InputStream in = getClass().getResourceAsStream("/hello.xml");
		
		// get document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		//	Document extends Node
		Document doc = db.parse(in);
		printNode(doc, 0);
	}
	
	public void printNode(Node n, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print(" ");
		}
		
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			System.out.printf("%s", n.getNodeName());
			printArrtributes(n);
			
		} else if (n.getNodeType() == Node.TEXT_NODE) {
			System.out.printf("%s", n.getNodeValue());
			printArrtributes(n);
		}
		
		for (Node n1 = n.getFirstChild(); n1 != null; n1 = n1.getNextSibling()) {
				printNode(n1, indent + 1);
		}
	}
	
	public void printArrtributes(Node n) {
		NamedNodeMap arrtris = n.getAttributes();
		if (arrtris == null || arrtris.getLength() == 0) {
			System.out.print("\n");
			return;
		}
		System.out.print("[");
		for (int i = 0; i < arrtris.getLength(); i++) {
			Node arrtri = arrtris.item(i);
			System.out.printf(" %s:%s ", arrtri.getNodeName(), arrtri.getNodeValue());
		}
		System.out.print("]\n");
	}
}
	
	
