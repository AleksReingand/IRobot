package com.aleks.irobot.factory;


import com.aleks.irobot.annotations.InjectByType;
import com.aleks.irobot.annotations.InjectRandomInt;
import com.aleks.irobot.config.MyConfig;
import com.aleks.irobot.configurators.ObjectConfigurator;
import lombok.Setter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MyObjectFactory
{
  public static MyObjectFactory myObjectFactory = new MyObjectFactory();

  @Setter
  private MyConfig config;
  private Reflections scanner = new Reflections("com/aleks/irobot");

  private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();

  @SneakyThrows
  private MyObjectFactory()
  {
    Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
    for(Class<? extends ObjectConfigurator> aClass : classes)
    {
      if(!Modifier.isAbstract(aClass.getModifiers()))
      {
        objectConfigurators.add(aClass.getDeclaredConstructor().newInstance());
      }
    }
  }

  public static MyObjectFactory getInstance()
  {
    return myObjectFactory;
  }

  @SneakyThrows
  public <T> T create(Class<T> type)
  {
    Class<? extends T> implClass = resolveImpl(type);
    T t = implClass.getDeclaredConstructor().newInstance();
    objectConfigurators.forEach(objectConfigurator -> objectConfigurator.configurator(t));

    return t;
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
