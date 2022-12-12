package com.my.entity;

import com.my.entity.Device;
import com.my.entity.Devices;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {


  public ObjectFactory() {
  }

  public Device createDevice() {
    return new Device();
  }

  public Devices createDevices() {
    return new Devices();
  }

  public Device.Options createDeviceOptions() {
    return new Device.Options();
  }

}
