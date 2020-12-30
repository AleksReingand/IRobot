package com.aleks.irobot;

import com.aleks.irobot.annotations.InjectRandomInt;
import lombok.Getter;

@Getter
public class Soldier
{
  @InjectRandomInt
  private int power;
}
