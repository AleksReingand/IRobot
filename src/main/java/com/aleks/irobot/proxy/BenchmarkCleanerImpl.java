//package com.aleks.irobot.proxy;
//
//import com.aleks.irobot.annotations.InjectByType;
//import com.aleks.irobot.functionality.RobotCleaner;
//import com.aleks.irobot.functionality.RobotCleanerImpl;
//
//public class BenchmarkCleanerImpl implements RobotCleaner
//{
//  @InjectByType()
//  private RobotCleanerImpl cleaner;
//
//  @Override
//  public void clean()
//  {
//    long start = System.nanoTime();
//    cleaner.clean();
//    long end = System.nanoTime();
//    System.out.println(end - start);
//  }
//}
