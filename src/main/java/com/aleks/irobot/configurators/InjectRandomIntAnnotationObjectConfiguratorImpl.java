package com.aleks.irobot.configurators;

import com.aleks.irobot.annotations.InjectRandomInt;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationObjectConfiguratorImpl implements ObjectConfigurator
{
  @Override
  @SneakyThrows
  public void configurator(Object t)
  {
    Class<?> implClass = t.getClass();
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
  }
}
