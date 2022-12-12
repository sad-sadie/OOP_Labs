package com.my.parser;

import com.my.entity.*;
import com.my.util.Key;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SAXParser extends AbstractParser {


  private String temp;
  private Device device;
  private Device.Options options;
  private boolean isOptions = false;
  private final ObjectFactory factory = new ObjectFactory();
  private final List<Device> deviceList = new ArrayList<>();

  public SAXParser(String name) {
    this.name = name;
  }

  @Override
  public Devices parse(String fileName) {
    try {
      XMLReader reader = XMLReaderFactory.createXMLReader();
      SaxHandler contentHandler = new SaxHandler();
      reader.setContentHandler(contentHandler);
      reader.parse(new InputSource(fileName));
    } catch (IOException | SAXException e) {
      Logger.getLogger(e.getMessage());
    }
    Devices devices = factory.createDevices();
    devices.setDevice(deviceList);
    return devices;
  }

  class SaxHandler implements ContentHandler {

    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void startDocument()  {
    }

    @Override
    public void endDocument()  {
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) {
    }

    @Override
    public void endPrefixMapping(String prefix)  {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
      temp = qName;
      if (Key.DEVICE.equals(localName)) {
        device = factory.createDevice();
        device.setId(Integer.valueOf(atts.getValue(Key.ID)));
      } else {
        if (Key.OPTIONS.equals(localName)) {
          options = factory.createDeviceOptions();
          isOptions = true;
        }
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
      if (isOptions) {
        device.setOptions(options);
      }
      isOptions = false;
      temp = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
      final boolean peripherals = Boolean.parseBoolean(new String(ch, start, length));
      switch (temp) {
        case Key.NAME -> device.setName(new String(ch, start, length));
        case Key.PRICE -> device.setPrice(Double.parseDouble(new String(ch, start, length)));
        case Key.ORIGIN -> device.setOrigin(new String(ch, start, length));
        case Key.COMPONENTS_GROUP -> device.setComponentsGroup(ComponentsGroup.valueOf(new String(ch, start, length)));
        case Key.PERIPHERALS -> options.setPeripherals(peripherals);
        case Key.ENERGY_CONSUMPTION -> options.setEnergyConsumption(Integer.parseInt(new String(ch, start, length)));
        case Key.PRESENT_COOLER -> options.setPresenceCooler(peripherals);
        case Key.PORTS -> {
          options.setPorts(Port.valueOf(new String(ch, start, length)));
          device.setOptions(options);
        }
        case Key.CRITICAL -> {
          device.setCritical(peripherals);
          deviceList.add(device);
        }
      }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)  {
    }

    @Override
    public void processingInstruction(String target, String data)  {
    }

    @Override
    public void skippedEntity(String name) {
    }
  }
}
