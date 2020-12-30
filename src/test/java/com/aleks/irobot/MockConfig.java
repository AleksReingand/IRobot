package com.aleks.irobot;

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
