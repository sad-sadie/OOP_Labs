package com.my.parser;

import com.my.entity.Devices;
import com.my.entity.Renderer;
import com.my.util.Key;
import com.my.util.Path;
import com.my.validator.XMLValidator;

import java.nio.file.Paths;
import java.util.Iterator;

public class ControllerParser implements Path, Key {

  public void makeParse() {
    Renderer renderer = new Renderer();
    String fileSeparator = System.getProperty(SEPARATOR);
    java.nio.file.Path path = Paths.get(FILE_PATH + FILE_NAME_XML).toAbsolutePath();
    Iterator<java.nio.file.Path> iterator = path.iterator();
    StringBuilder resultPath = new StringBuilder(path.getRoot().toString());
    while (iterator.hasNext()) {
      resultPath.append(fileSeparator).append(iterator.next());
    }

    java.nio.file.Path pathXSD = Paths.get(FILE_PATH + FILE_NAME_XSD).toAbsolutePath();
    Iterator<java.nio.file.Path> iteratorXSD = pathXSD.iterator();
    StringBuilder resultPathXSD = new StringBuilder(pathXSD.getRoot().toString());
    while (iteratorXSD.hasNext()) {
      resultPathXSD.append(fileSeparator).append(iteratorXSD.next());
    }

    boolean validation = new XMLValidator().valid(resultPath.toString(), resultPathXSD.toString());

    Parser parser;
    if (validation) {
      for (ParserType type : ParserType.values()) {
        parser = ParserFactory.getInstance().create(type);
        renderer.showLn(parser.name());

        Devices devices = parser.parse(resultPath.toString());
        renderer.showDevises(devices);

        String fileName = FILE_PATH + parser.name() + RESULT;
        parser.createXML(fileName, devices);
      }
    }
  }
}
