package com.aleks.irobot.configurators;

import com.aleks.irobot.annotations.InjectByType;
import com.aleks.irobot.factory.MyObjectFactory;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfiguratorImpl implements ObjectConfigurator
{
  @Override
  @SneakyThrows
  public void configurator(Object t)
  {
    Field[] fields = t.getClass().getDeclaredFields();
    for(Field field : fields)
    {
      if(field.isAnnotationPresent(InjectByType.class))
      {
        Object value = MyObjectFactory.getInstance().create(field.getType());
        field.setAccessible(true);
        field.set(t, value);
      }
    }
  }
}
