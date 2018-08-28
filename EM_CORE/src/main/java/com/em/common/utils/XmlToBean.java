package com.em.common.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToBean {

	/**
	 * 14 * xml文件配置转换为对象 15 * @param xmlPath xml文件路径 16 * @param load
	 * java对象.Class 17 * @return java对象 18 * @throws JAXBException 19 * @throws
	 * IOException 20
	 */
	public static Object xmlToBean(String xmlPath, Class<?> load) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(load);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object object = unmarshaller.unmarshal(new File(xmlPath));
		return object;
	}

}