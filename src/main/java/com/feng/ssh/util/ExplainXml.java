/**
 * ExplainXml.java
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年4月7日 		wuhoushuang
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
 */

package com.feng.ssh.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class ExplainXml {
	@SuppressWarnings("rawtypes")
	public List xmlElements(String xmlDoc) {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		List jiedian = null;
		List<String> context = new ArrayList<String>();
		try {
			// 通过输入源构造一个Document
			Document doc = null;
			try {
				doc = sb.build(source);
			} catch (JDOMException e) {
				e.printStackTrace();
			}
			// 取的根元素
			Element root = doc.getRootElement();
			// 得到根元素所有子元素的集合
			jiedian = root.getChildren();
			// 获得XML中的命名空间（XML中未定义可不写）
			for (int i = 0; i < jiedian.size(); i++) {
				Element xet = (Element) jiedian.get(i);
				String text = xet.getText();
				context.add(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return context;
	}

}
