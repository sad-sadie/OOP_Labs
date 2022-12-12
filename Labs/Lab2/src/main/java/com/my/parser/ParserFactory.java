package com.my.parser;

public class ParserFactory {


  private static final ParserFactory parserFactory = new ParserFactory();


  private ParserFactory() {
  }

  public static ParserFactory getInstance() {
    return parserFactory;
  }


  public Parser create(ParserType type) {
    Parser result = null;
    switch (type) {
      case DOMParser -> result = new DOMParser(ParserType.DOMParser.toString());
      case SAXParser -> result = new SAXParser(ParserType.SAXParser.toString());
      case StAXParser -> result = new StAXParser(ParserType.StAXParser.toString());
    }
    return result;
  }
}
