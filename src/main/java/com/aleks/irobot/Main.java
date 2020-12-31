package com.aleks.irobot;

import com.aleks.irobot.config.JavaConfig;
import com.aleks.irobot.factory.MyObjectFactory;
import com.aleks.irobot.functionality.ConsoleSpeakerImpl;
import com.aleks.irobot.functionality.Speaker;

import java.util.Map;

public class Main
{
  public static void main(String[] args)
  {
    Map<Class<?>, Class<?>> map = Map.of(Speaker.class, ConsoleSpeakerImpl.class);
    MyObjectFactory.getInstance().setConfig(new JavaConfig(map, ""));

    IRobot robot = MyObjectFactory.getInstance().create(IRobot.class);
    robot.cleanRoom();
  }
}
