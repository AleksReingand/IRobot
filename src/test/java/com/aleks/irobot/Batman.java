package com.aleks.irobot;

import lombok.Getter;

@Getter
public class Batman implements SuperHero
{
  @InjectRandomInt
  private int power;

  @Override
  public String getName()
  {
    return "Batman";
  }

}
