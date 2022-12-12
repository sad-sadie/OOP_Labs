package com.my.parser;

import com.my.entity.Devices;

public interface Parser {


  String name();
  Devices parse(String fileName);
  void createXML(String fileName, Devices devices);
}
