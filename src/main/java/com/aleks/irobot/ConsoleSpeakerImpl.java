package com.aleks.irobot;

public class ConsoleSpeakerImpl implements Speaker
{
  public void speak(String msg)
  {
    System.out.println(msg);
  }
}
