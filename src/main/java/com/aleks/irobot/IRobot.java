package com.aleks.irobot;


import com.aleks.irobot.factory.MyObjectFactory;
import com.aleks.irobot.functionality.RobotCleaner;
import com.aleks.irobot.functionality.Speaker;

public class IRobot
{
  private Speaker speaker = MyObjectFactory.getInstance().create(Speaker.class);
  private RobotCleaner cleaner = MyObjectFactory.getInstance().create(RobotCleaner.class);

  public void cleanRoom()
  {
    speaker.speak("I started");
    cleaner.clean();
    speaker.speak("I finished");
  }

}
