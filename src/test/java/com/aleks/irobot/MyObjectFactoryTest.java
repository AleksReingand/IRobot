package com.aleks.irobot;

import com.aleks.irobot.config.Config;
import com.aleks.irobot.config.JavaConfig;
import com.aleks.irobot.factory.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MyObjectFactoryTest
{
  private ApplicationContext context;

  @Before
  public void setUp()
  {
    context = new ApplicationContext(new JavaConfig(null, "com/aleks/irobot"));
  }

  @org.junit.Test
  public void injectRandomIntIsWorking()
  {
    Soldier soldier = context.getBean(Soldier.class);
    Assert.assertTrue(soldier.getPower() >= 1 && soldier.getPower() <= 5);
  }

  @org.junit.Test
  public void objectWasCreatedFromConfiguredClass()
  {
    Config config = Mockito.mock(Config.class);
    Mockito.when(config.getPackageToScan()).thenReturn("com/aleks/irobot");
    Mockito.when(config.getImpl(SuperHero.class)).then(invocationOnMock -> Batman.class);

    ApplicationContext context = new ApplicationContext(config);

    SuperHero superHero = context.getBean(SuperHero.class);
    Assert.assertEquals(Batman.class, superHero.getClass());
  }

  @Test
  public void testSingletonWillBeCreatedOnlyOnce()
  {
    MySingleton bean1 = context.getBean(MySingleton.class);
    MySingleton bean2 = context.getBean(MySingleton.class);
    context.getBean(MySingleton.class);

    Assert.assertTrue(bean1 == bean2);
    Assert.assertEquals(1, MySingleton.counter);
  }
}