package com.my.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Port", namespace = "http://sadie.com.lab2/devices")
@XmlEnum
public enum Port {
  COM,
  USB,
  LPT;

  public String value() {
    return name();
  }

  public static Port fromValue(String v) {
    return valueOf(v);
  }

}
