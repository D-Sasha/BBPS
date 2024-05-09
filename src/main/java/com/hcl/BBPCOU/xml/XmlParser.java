package com.hcl.BBPCOU.xml;

import jakarta.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlParser {

    public static <T> T parse(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
    }

    public static String serialize(Object obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
}
