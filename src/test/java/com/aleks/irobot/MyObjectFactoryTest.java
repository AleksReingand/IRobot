package com.aleks.irobot;

import com.aleks.irobot.config.MyConfig;
import com.aleks.irobot.factory.MyObjectFactory;
import org.junit.Assert;
import org.mockito.Mockito;

public class MyObjectFactoryTest
{

  @org.junit.Test
  public void injectRandomIntIsWorking()
  {
    Soldier soldier = MyObjectFactory.getInstance().create(Soldier.class);
    Assert.assertTrue(soldier.getPower() >= 1 && soldier.getPower() <= 5);
  }

  @org.junit.Test
  public void objectWasCreatedFromConfiguredClass()
  {
    MyConfig config = Mockito.mock(MyConfig.class);
    Mockito.when(config.getImpl(SuperHero.class)).then(invocationOnMock -> Batman.class);

    MyObjectFactory.getInstance().setConfig(config);
    SuperHero superHero = MyObjectFactory.getInstance().create(SuperHero.class);
    Assert.assertEquals(Batman.class, superHero.getClass());
//    Assert.assertNotEquals(0, superHero.getPower());
  }
}