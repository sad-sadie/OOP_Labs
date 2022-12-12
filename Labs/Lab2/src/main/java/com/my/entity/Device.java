package com.my.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", namespace = "http://sadie.com.lab2/devices", propOrder = {
    "name",
    "origin",
    "price",
    "componentsGroup",
    "options",
    "critical"
})
public class Device {

  @XmlElement(name = "Name", namespace = "http://katruk.com.task3/devices", required = true)
  protected String name;
  @XmlElement(name = "Origin", namespace = "http://katruk.com.task3/devices", required = true)
  protected String origin;
  @XmlElement(name = "Price", namespace = "http://katruk.com.task3/devices")
  protected double price;
  @XmlElement(name = "ComponentsGroup", namespace = "http://katruk.com.task3/devices", required = true)
  protected ComponentsGroup componentsGroup;
  @XmlElement(name = "Options", namespace = "http://katruk.com.task3/devices", required = true)
  protected Device.Options options;
  @XmlElement(name = "Critical", namespace = "http://katruk.com.task3/devices")
  protected boolean critical;
  @XmlAttribute(name = "id")
  protected Integer id;

  @Override
  public String toString() {
    return "    Device{" +
           "name='" + name + '\'' +
           ", origin='" + origin + '\'' +
           ", price=" + price +
           ", componentsGroup=" + componentsGroup +
           ",\n        options=" + options +
           ", critical=" + critical +
           ", id=" + id +
           '}';
  }


  public String getName() {
    return name;
  }


  public void setName(String value) {
    this.name = value;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String value) {
    this.origin = value;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double value) {
    this.price = value;
  }

  public ComponentsGroup getComponentsGroup() {
    return componentsGroup;
  }

  public void setComponentsGroup(ComponentsGroup value) {
    this.componentsGroup = value;
  }

  public Device.Options getOptions() {
    return options;
  }


  public void setOptions(Device.Options value) {
    this.options = value;
  }

  public boolean isCritical() {
    return critical;
  }


  public void setCritical(boolean value) {
    this.critical = value;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer value) {
    this.id = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Device device = (Device) o;
    return Double.compare(device.price, price) == 0 && critical == device.critical && name.equals(device.name) && origin.equals(device.origin) && componentsGroup == device.componentsGroup && options.equals(device.options) && id.equals(device.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, origin, price, componentsGroup, options, critical, id);
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "peripherals",
      "energyConsumption",
      "presenceCooler",
      "ports"
  })

  public static class Options {

    @XmlElement(name = "Peripherals", namespace = "http://katruk.com.task3/devices")
    protected boolean peripherals;
    @XmlElement(name = "EnergyConsumption", namespace = "http://katruk.com.task3/devices")
    protected int energyConsumption;
    @XmlElement(name = "PresenceCooler", namespace = "http://katruk.com.task3/devices")
    protected boolean presenceCooler;
    @XmlElement(name = "Ports", namespace = "http://katruk.com.task3/devices", required = true)
    protected Port ports;

    @Override
    public String toString() {
      return "Options{" +
             "peripherals=" + peripherals +
             ", energyConsumption=" + energyConsumption +
             ", presenceCooler=" + presenceCooler +
             ", ports=" + ports +
             '}';
    }

    public boolean isPeripherals() {
      return peripherals;
    }

    public void setPeripherals(boolean value) {
      this.peripherals = value;
    }

    public int getEnergyConsumption() {
      return energyConsumption;
    }

    public void setEnergyConsumption(int value) {
      this.energyConsumption = value;
    }

    public boolean isPresenceCooler() {
      return presenceCooler;
    }

    public void setPresenceCooler(boolean value) {
      this.presenceCooler = value;
    }

    public Port getPorts() {
      return ports;
    }

    public void setPorts(Port value) {
      this.ports = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Options options = (Options) o;
      return peripherals == options.peripherals && energyConsumption == options.energyConsumption && presenceCooler == options.presenceCooler && ports == options.ports;
    }

    @Override
    public int hashCode() {
      return Objects.hash(peripherals, energyConsumption, presenceCooler, ports);
    }
  }

}
