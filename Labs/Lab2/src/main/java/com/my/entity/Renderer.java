package com.my.entity;

import com.my.entity.Devices;

public class Renderer {


  public void showLn(String string) {
    System.out.println(string);
  }

  public void showDevises(Devices devices) {
    devices.getDevice().forEach(System.out::println);
  }
}
