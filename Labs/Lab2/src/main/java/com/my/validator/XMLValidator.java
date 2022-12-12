package com.my.validator;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public class XMLValidator {

  public boolean valid(String fileName, String schemaName) {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      Schema schema = factory.newSchema(new File(schemaName));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(fileName));
      return true;
    } catch (SAXException | IOException e) {
      Logger.getLogger(e.getMessage());
    }
    return false;
  }
}
