package com.aleks.irobot;

import java.util.Map;

public class JavaConfig implements MyConfig
{
  private final Map<Class<?>, Class<?>> ifc2ImplClass;

  public JavaConfig(Map<Class<?>, Class<?>> map)
  {
    this.ifc2ImplClass = map;
  }

  @Override
  public <T> Class<T> getImpl(Class<T> type)
  {
    return (Class<T>) ifc2ImplClass.get(type);
  }
}
