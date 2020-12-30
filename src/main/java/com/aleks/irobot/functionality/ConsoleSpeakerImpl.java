package com.aleks.irobot.functionality;

public class ConsoleSpeakerImpl implements Speaker
{
  public void speak(String msg)
  {
    System.out.println(msg);
  }
}
