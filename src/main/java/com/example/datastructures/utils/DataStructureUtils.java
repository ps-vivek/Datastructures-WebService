/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DataStructureUtils {

	public static String convertJavaObjToXml(JAXBContext context, Object object) {
		StringWriter xmlResponse = new StringWriter();
		try {

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, xmlResponse);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return xmlResponse.toString();
	}

}
