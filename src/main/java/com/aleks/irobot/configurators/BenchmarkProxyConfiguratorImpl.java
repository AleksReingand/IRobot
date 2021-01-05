package com.aleks.irobot.configurators;

import com.aleks.irobot.annotations.Benchmark;
import com.aleks.irobot.factory.ApplicationContext;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.annotation.Retention;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BenchmarkProxyConfiguratorImpl implements ProxyConfigurator
{
  @Override
  public Object wrapWithProxy(ApplicationContext context, Object t, Class<?> implClass)
  {
    if(implClass.isAnnotationPresent(Benchmark.class)
            || ReflectionUtils.getAllMethods(implClass).stream().anyMatch(method -> method.isAnnotationPresent(Benchmark.class)))
    {
      return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler()
      {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
          if(implClass.isAnnotationPresent(Benchmark.class)
                  || implClass.getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Benchmark.class))
          {
            System.out.println("****** Benchmark started for method " + method.getName());
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.println(end - start);
            System.out.println("****** Benchmark finished for method " + method.getName());

            return retVal;
          }
          else
          {
            return method.invoke(t, args);
          }
        }
      });
    }
    else
    {
      return t;
    }
  }
}
