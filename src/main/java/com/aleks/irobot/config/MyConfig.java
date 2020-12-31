package com.aleks.irobot.config;

public interface MyConfig
{
  <T> Class<? extends T> getImpl(Class<T> type);
  String getPackageToScan();
}
