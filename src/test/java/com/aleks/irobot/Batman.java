package com.aleks.irobot;

import com.aleks.irobot.annotations.InjectRandomInt;
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
