package com.aleks.irobot.configurators;

import com.aleks.irobot.factory.ApplicationContext;
import com.aleks.irobot.factory.ObjectFactory;

public interface ObjectConfigurator
{
  void configurator(Object t, ApplicationContext context);
}
