package com.my.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "device"
})
@XmlRootElement(name = "Devices", namespace = "http://sadie.com.lab2/devices")
public class Devices {

  @XmlElement(name = "Device", namespace = "http://sadie.com.lab2/devices", required = true)
  protected List<Device> device;

  public List<Device> getDevice() {
    if (device == null) {
      device = new ArrayList<>();
    }
    return this.device;
  }

  public void setDevice(List<Device> device) {
    this.device = device;
  }
}
