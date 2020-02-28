package com.linxb.common.component.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public static Map<String, String> parseXml(String msgXml) throws DocumentException {
        Map<String, String> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(msgXml.getBytes()));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String name = element.getName();
            String text = element.getText();
            map.put(name, text);
        }
        return map;
    }
}
