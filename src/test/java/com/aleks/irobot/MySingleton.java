package com.aleks.irobot;

import com.aleks.irobot.annotations.Singleton;

@Singleton
public class MySingleton
{
  public static int counter;

  public MySingleton()
  {
    counter++;
  }
}
