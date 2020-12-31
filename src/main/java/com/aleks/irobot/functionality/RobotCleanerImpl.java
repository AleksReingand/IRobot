package com.aleks.irobot.functionality;

import com.aleks.irobot.annotations.InjectRandomInt;

public class RobotCleanerImpl implements RobotCleaner
{
  @InjectRandomInt
  public int repeat;

  public void clean()
  {
    for(int i = 0; i < repeat; i++)
    {
      System.out.println("Vvvvvvvv");
    }
  }
}
