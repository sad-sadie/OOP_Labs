package com.my.parser;

import com.my.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DOMParser extends AbstractParser {


  public DOMParser(String name) {
    this.name = name;
  }

  @Override
  public Devices parse(String fileName) {
    Devices devices = new ObjectFactory().createDevices();

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document document = dBuilder.parse(new File(fileName));
      document.getDocumentElement().normalize();
      Element root = document.getDocumentElement();

      NodeList deviceNodes = root.getElementsByTagName(DEVICE);

      for (int i = 0; i < deviceNodes.getLength(); i++) {
        Device device = new ObjectFactory().createDevice();
        Element deviseElement = (Element) deviceNodes.item(i);
        device.setId(Integer.parseInt(deviseElement.getAttribute(ID)));
        device.setName(getChildValue(deviseElement, NAME));
        device.setPrice(Double.parseDouble(getChildValue(deviseElement, PRICE)));
        device.setOrigin(getChildValue(deviseElement, ORIGIN));
        device.setComponentsGroup(
            ComponentsGroup.valueOf(getChildValue(deviseElement, COMPONENTS_GROUP)));
        Device.Options options = new ObjectFactory().createDeviceOptions();
        Element optionsElement = getChild(deviseElement, OPTIONS);
        options.setPeripherals(Boolean.parseBoolean(getChildValue(optionsElement, PERIPHERALS)));
        options.setEnergyConsumption(
            Integer.parseInt(getChildValue(optionsElement, ENERGY_CONSUMPTION)));
        options.setPresenceCooler(Boolean.parseBoolean(getChildValue(optionsElement, PRESENT_COOLER)));
        options.setPorts(Port.valueOf(getChildValue(optionsElement, PORTS)));
        device.setOptions(options);
        device.setCritical(Boolean.parseBoolean(getChildValue(deviseElement, CRITICAL)));
        devices.getDevice().add(device);
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      Logger.getLogger(e.getMessage());
    }
    return devices;
  }


  private String getChildValue(Element parent, String childName) {
    Element child = getChild(parent, childName);
    Node node = child.getFirstChild();
    return node.getNodeValue();
  }


  private Element getChild(Element parent, String childName) {
    NodeList nodeList = parent.getElementsByTagName(childName);
    return (Element) nodeList.item(0);
  }

}
