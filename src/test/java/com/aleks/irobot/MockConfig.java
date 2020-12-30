package com.aleks.irobot;

import com.aleks.irobot.config.MyConfig;

public class MockConfig implements MyConfig
{
  @Override
  public <T> Class<T> getImpl(Class<T> type)
  {
    if(type == SuperHero.class)
    {
      return (Class<T>) Batman.class;
    }

    throw new UnsupportedOperationException("wrong");
  }
}
