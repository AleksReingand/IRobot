package com.aleks.irobot.factory;

import com.aleks.irobot.annotations.Singleton;
import com.aleks.irobot.config.Config;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContext
{
  private final Config config;
  private ObjectFactory objectFactory;
  private Reflections scanner;
  private Map<Class<?>, Object> cash = new HashMap<>();

  public ApplicationContext(Config config)
  {
    this.config = config;
    scanner = new Reflections(config.getPackageToScan());
    objectFactory = new ObjectFactory(scanner, this);
  }

  public <T> T getBean(Class<T> type)
  {
    if(cash.containsKey(type))
    {
      return (T) cash.get(type);
    }
    Class<? extends T> implClass = resolveImpl(type);
    T aClass = objectFactory.create(implClass);
    if(implClass.isAnnotationPresent(Singleton.class))
    {
      cash.put(type, aClass);
    }

    return aClass;
  }

  private <T> Class<? extends T> resolveImpl(Class<T> type)
  {
    Class<? extends T> implClass;
    if(type.isInterface())
    {
      implClass = config.getImpl(type);
      if(implClass == null)
      {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
        if(classes.size() != 1)
        {
          throw new IllegalStateException(type + " has 0 or more");
        }
        implClass = classes.iterator().next();
      }
    }
    else
    {
      implClass = type;
    }
    return implClass;
  }
}
