package com.aleks.irobot.configurators;

import com.aleks.irobot.factory.ApplicationContext;

public interface ProxyConfigurator
{
  Object wrapWithProxy(ApplicationContext context, Object t, Class<?> implClass);
}
