package com.aleks.irobot;

public interface MyConfig
{
  <T> Class<T> getImpl(Class<T> type);
}
