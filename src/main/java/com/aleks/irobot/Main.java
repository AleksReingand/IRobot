package com.aleks.irobot;

import com.aleks.irobot.config.JavaConfig;
import com.aleks.irobot.factory.ApplicationContext;
import com.aleks.irobot.functionality.ConsoleSpeakerImpl;
import com.aleks.irobot.functionality.Speaker;

import java.util.Map;

public class Main
{
  public static void main(String[] args)
  {
    Map<Class<?>, Class<?>> map = Map.of(Speaker.class, ConsoleSpeakerImpl.class);

    JavaConfig config = new JavaConfig(map, "");
    ApplicationContext context = new ApplicationContext(config);

    IRobot robot = context.getBean(IRobot.class);

    robot.cleanRoom();
  }
}
