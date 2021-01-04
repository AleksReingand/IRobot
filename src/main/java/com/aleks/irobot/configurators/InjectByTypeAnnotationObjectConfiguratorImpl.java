package com.aleks.irobot.configurators;

import com.aleks.irobot.annotations.InjectByType;
import com.aleks.irobot.factory.ApplicationContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfiguratorImpl implements ObjectConfigurator
{
  @Override
  @SneakyThrows
  public void configurator(Object t, ApplicationContext context)
  {
    Field[] fields = t.getClass().getDeclaredFields();
    for(Field field : fields)
    {
      if(field.isAnnotationPresent(InjectByType.class))
      {
        Object value = context.getBean(field.getType());
        field.setAccessible(true);
        field.set(t, value);
      }
    }
  }
}
