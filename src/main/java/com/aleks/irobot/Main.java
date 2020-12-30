package com.aleks.irobot;

import java.util.Map;

public class Main
{
  public static void main(String[] args)
  {
    Map<Class<?>, Class<?>> map = Map.of(Speaker.class, ConsoleSpeakerImpl.class);
    MyObjectFactory.getInstance().setConfig(new JavaConfig(map));

    IRobot robot = new IRobot();
    robot.cleanRoom();
  }
}
