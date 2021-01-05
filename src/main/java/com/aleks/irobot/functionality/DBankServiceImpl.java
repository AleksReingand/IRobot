package com.aleks.irobot.functionality;

import com.aleks.irobot.annotations.Benchmark;
import com.aleks.irobot.config.JavaConfig;
import com.aleks.irobot.factory.ApplicationContext;

import java.util.Map;

public class DBankServiceImpl implements DBankService
{
  @Override
  public void doWork()
  {
    System.out.println("Go to work ...");
  }

  @Override
  @Benchmark
  public void doDrink()
  {
    System.out.println("drinking ...");
  }

  public static void main(String[] args)
  {
    ApplicationContext context = new ApplicationContext(new JavaConfig(Map.of(), "com/aleks/irobot"));
    DBankService service = context.getBean(DBankService.class);
    service.doWork();
    service.doDrink();

  }
}
