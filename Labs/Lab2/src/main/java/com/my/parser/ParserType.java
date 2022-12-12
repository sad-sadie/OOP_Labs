package com.my.parser;

public enum ParserType {
  DOMParser,
  SAXParser,
  StAXParser;

  @Override
  public String toString() {
    return name();
  }
}
