package com.my.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "ComponentsGroup", namespace = "http://sadie.com.lab2/devices")
@XmlEnum
public enum ComponentsGroup {

  @XmlEnumValue("I/O")
  I_O("I/O"),
  MULTIMEDIA("MULTIMEDIA"),
  SOUND("SOUND"),
  VIDEO("VIDEO"),
  PRINTER("PRINTER");

  private final String value;

  ComponentsGroup(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static ComponentsGroup fromValue(String v) {
    for (ComponentsGroup c : ComponentsGroup.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
