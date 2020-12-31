package com.aleks.irobot;


import com.aleks.irobot.annotations.InjectByType;
import com.aleks.irobot.functionality.RobotCleaner;
import com.aleks.irobot.functionality.Speaker;

public class IRobot
{
  @InjectByType(requared = false)
  private Speaker speaker;
  @InjectByType(requared = false)
  private RobotCleaner cleaner;

  public void cleanRoom()
  {
    speaker.speak("I started");
    cleaner.clean();
    speaker.speak("I finished");
  }

}
