package com.my.parser;

import com.my.entity.*;
import com.my.util.ControllerFile;
import com.my.util.EncodingText;
import com.my.util.Key;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXParser extends AbstractParser {


  public StAXParser(String name) {
    this.name = name;
  }

  @Override
  public Devices parse(String fileName) {
    ObjectFactory objectFactory = new ObjectFactory();
    String tempContent = null;
    Devices devices = null;
    Device device = null;
    Device.Options options = null;

    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = null;
    ControllerFile controllerFile = new ControllerFile();

    String xmlAsString = null;
    try {
      xmlAsString = controllerFile.readFile(fileName, EncodingText.Utf8);
    } catch (IOException e) {
      Logger.getLogger(e.getMessage());
    }

    try {
      StringReader stringReader = new StringReader(xmlAsString);
      reader = factory.createXMLStreamReader(stringReader);
    } catch (XMLStreamException e) {
      Logger.getLogger(e.getMessage());
    }

    try {
      while (reader.hasNext()) {

        int event = reader.next();

        switch (event) {
          case XMLStreamConstants.START_ELEMENT:
            if (Key.DEVICES.equals(reader.getLocalName())) {
              devices = objectFactory.createDevices();
            } else if (Key.DEVICE.equals(reader.getLocalName())) {
              device = objectFactory.createDevice();
              device.setId(Integer.valueOf(reader.getAttributeValue(0)));
              devices.getDevice().add(device);
            } else if (Key.OPTIONS.equals(reader.getLocalName())) {
              options = objectFactory.createDeviceOptions();
              device.setOptions(options);
            }
            break;

          case XMLStreamConstants.CHARACTERS:
            tempContent = reader.getText().trim();
            break;

          case XMLStreamConstants.END_ELEMENT:
            switch (reader.getLocalName()) {
              case Key.NAME:
                assert device != null;
                device.setName(tempContent);
                break;
              case Key.PRICE:
                device.setPrice(Double.valueOf(tempContent));
                break;
              case Key.ORIGIN:
                device.setOrigin(tempContent);
                break;
              case Key.COMPONENTS_GROUP:
                device.setComponentsGroup(ComponentsGroup.valueOf(tempContent));
                break;
              case Key.PERIPHERALS:
                options.setPeripherals(Boolean.valueOf(tempContent));
                break;
              case Key.ENERGY_CONSUMPTION:
                options.setEnergyConsumption(Integer.valueOf(tempContent));
                break;
              case Key.PRESENT_COOLER:
                options.setPresenceCooler(Boolean.valueOf(tempContent));
                break;
              case Key.PORTS:
                options.setPorts(Port.valueOf(tempContent));
                device.setOptions(options);
                break;
              case Key.CRITICAL:
                device.setCritical(Boolean.valueOf(tempContent));
                break;
            }
            break;
        }
      }
    } catch (XMLStreamException e) {
      Logger.getLogger(e.getMessage());
    }
    return devices;
  }
}

