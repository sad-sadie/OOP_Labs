package com.my.parser;

import com.my.entity.Device;
import com.my.entity.Devices;
import com.my.util.Key;

import java.io.FileOutputStream;
import java.util.logging.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


public abstract class AbstractParser implements Parser, Key {

  protected String name;

  @Override
  public String name() {
    return name;
  }

  @Override
  public void createXML(String fileName, Devices devices) {
    XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    try {
      XMLStreamWriter xmlw = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(fileName));
      xmlw.writeStartDocument();
      xmlw.writeCharacters(NEW_LINE);
      xmlw.writeStartElement(DEVICES);
      xmlw.writeNamespace(XMLNS, XMLNS_URL);
      xmlw.writeNamespace(PREFIX_SCHEMA, PREFIX_SCHEMA_URL);
      xmlw.writeAttribute(SCHEMA_LOCATION, SCHEMA_LOCATION_URL);
      xmlw.writeCharacters(NEW_LINE);
      for (Device device : devices.getDevice()) {
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(DEVICE);
        xmlw.writeAttribute(ID, String.valueOf(device.getId()));
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(NAME);
        xmlw.writeCharacters(device.getName());
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(ORIGIN);
        xmlw.writeCharacters(String.valueOf(device.getOrigin()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(PRICE);
        xmlw.writeCharacters(String.valueOf(device.getPrice()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(COMPONENTS_GROUP);
        xmlw.writeCharacters(String.valueOf(device.getComponentsGroup()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(OPTIONS);
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        Device.Options options = device.getOptions();
        xmlw.writeStartElement(PERIPHERALS);
        xmlw.writeCharacters(String.valueOf(options.isPresenceCooler()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(ENERGY_CONSUMPTION);
        xmlw.writeCharacters(String.valueOf(options.getEnergyConsumption()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(PRESENT_COOLER);
        xmlw.writeCharacters(String.valueOf(options.isPresenceCooler()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(PORTS);
        xmlw.writeCharacters(String.valueOf(options.getPorts()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeCharacters(TAB);
        xmlw.writeStartElement(CRITICAL);
        xmlw.writeCharacters(String.valueOf(device.isCritical()));
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
        xmlw.writeCharacters(TAB);
        xmlw.writeEndElement();
        xmlw.writeCharacters(NEW_LINE);
      }
      xmlw.writeEndElement();
      xmlw.writeEndDocument();
      xmlw.close();
    } catch (Exception e) {
      Logger.getLogger(e.getMessage());
    }
  }
}
