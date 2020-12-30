package com.aleks.irobot;


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
