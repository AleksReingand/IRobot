package com.aleks.irobot.factory;


import com.aleks.irobot.annotations.InjectRandomInt;
import com.aleks.irobot.config.MyConfig;
import lombok.Setter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Set;

public class MyObjectFactory
{
  public static MyObjectFactory myObjectFactory = new MyObjectFactory();

  @Setter
  private MyConfig config;
  private Reflections scanner = new Reflections("com/aleks/irobot/functionality");

  private MyObjectFactory()
  {
  }

  public static MyObjectFactory getInstance()
  {
    return myObjectFactory;
  }

  @SneakyThrows
  public <T> T create(Class<T> type)
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

    T t = implClass.getDeclaredConstructor().newInstance();

    Field[] declaredFields = implClass.getDeclaredFields();
    for(Field field: declaredFields)
    {
      InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
      if(annotation != null)
      {
        int randomInt = new Random().nextInt(annotation.max() - annotation.min()) + annotation.min();
        field.setAccessible(true);
        field.set(t, randomInt);
      }
    }

    return t;
  }
}
