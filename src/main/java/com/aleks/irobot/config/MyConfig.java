package com.aleks.irobot.config;

public interface MyConfig
{
  <T> Class<T> getImpl(Class<T> type);
}
