package com.aleks.irobot.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class JavaConfig implements Config
{
  private final Map<Class<?>, Class<?>> ifc2ImplClass;
  private final String packageToScan;

  @Override
  public <T> Class<? extends T> getImpl(Class<T> type)
  {
    return (Class<? extends T>) ifc2ImplClass.get(type);
  }
}
