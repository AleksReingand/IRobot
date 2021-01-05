package com.aleks.irobot.factory;


import com.aleks.irobot.annotations.Benchmark;
import com.aleks.irobot.configurators.ObjectConfigurator;
import com.aleks.irobot.configurators.ProxyConfigurator;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObjectFactory
{
  private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
  private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();
  private ApplicationContext context;

  @SneakyThrows
  ObjectFactory(Reflections scanner, ApplicationContext context)
  {
    this.context = context;

    Set<Class<? extends ObjectConfigurator>> objectConfList = scanner.getSubTypesOf(ObjectConfigurator.class);
    for(Class<? extends ObjectConfigurator> aClass : objectConfList)
    {
      if(!Modifier.isAbstract(aClass.getModifiers()))
      {
        objectConfigurators.add(aClass.getDeclaredConstructor().newInstance());
      }
    }

    Set<Class<? extends ProxyConfigurator>> proxyConfList = scanner.getSubTypesOf(ProxyConfigurator.class);
    for(Class<? extends ProxyConfigurator> aClass : proxyConfList)
    {
      if(!Modifier.isAbstract(aClass.getModifiers()))
      {
        proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
      }
    }
  }

  @SneakyThrows
  public <T> T create(Class<T> implClass)
  {
    T t = implClass.getDeclaredConstructor().newInstance();
    configure(t);
    t = configureProxy(implClass, t);

    return t;
  }

  private <T> void configure(T t)
  {
    objectConfigurators.forEach(objectConfigurator -> objectConfigurator.configurator(t, context));
  }

  private <T> T configureProxy(Class<T> implClass, T t)
  {
    for(ProxyConfigurator proxyConfigurator : proxyConfigurators)
    {
      t = (T) proxyConfigurator.wrapWithProxy(context, t, implClass);
    }
    return t;
  }
}
