package com.aleks.irobot.factory;


import com.aleks.irobot.annotations.Benchmark;
import com.aleks.irobot.configurators.ObjectConfigurator;
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
  private ApplicationContext context;

  @SneakyThrows
  ObjectFactory(Reflections scanner, ApplicationContext context)
  {
    this.context = context;
    Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
    for(Class<? extends ObjectConfigurator> aClass : classes)
    {
      if(!Modifier.isAbstract(aClass.getModifiers()))
      {
        objectConfigurators.add(aClass.getDeclaredConstructor().newInstance());
      }
    }
  }

  @SneakyThrows
  public <T> T create(Class<T> implClass)
  {
    T t = implClass.getDeclaredConstructor().newInstance();
    objectConfigurators.forEach(objectConfigurator -> objectConfigurator.configurator(t, context));

    if(implClass.isAnnotationPresent(Benchmark.class))
    {
      return (T) Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler()
      {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
          System.out.println("****** Benchmark started for method " + method.getName());
          long start = System.nanoTime();
          Object retVal = method.invoke(t, args);
          long end = System.nanoTime();
          System.out.println("****** Benchmark finished for method " + method.getName());

          return retVal;
        }
      });
    }

    return t;
  }
}
