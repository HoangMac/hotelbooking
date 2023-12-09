package com.assessment.hotelbooking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import com.assessment.hotelbooking.config.SchedulerConfig;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {SchedulerConfig.class, ReferenceNumberGeneratorTest.TestConfig.class})
class ReferenceNumberGeneratorTest {
  @Autowired
  private ReferenceNumberGenerator generator;

//  @Test
  void testGenerateRefNo_given2ThreadExecuteSameTime() throws ExecutionException, InterruptedException {

    Callable<String> callable = () -> generator.generateRefNo();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<String> future1 = executorService.submit(callable);
    Future<String> future2 = executorService.submit(callable);

    while(!future1.isDone() || !future2.isDone()) {
    }

    var refNo1 = future1.get();
    var refNo2 = future2.get();

    assertEquals(refNo2.substring(0, refNo2.length() - 3), refNo1.substring(0, refNo1.length() - 3));
    assertEquals(1, Math.abs(Integer.parseInt(refNo2.substring(refNo2.length() - 3)) -
        Integer.parseInt(refNo1.substring(refNo1.length() - 3))));


    executorService.shutdown();
  }

//  @Test
  void testGenerateRefNo_given2ThreadExecute1SecDiff() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<String> future1 = executorService.submit(() -> generator.generateRefNo());
    Future<String> future2 = executorService.submit(() -> {
      Thread.sleep(1000);
      return generator.generateRefNo();
    });

    while(!future1.isDone() || !future2.isDone()) {
    }

    var refNo1 = future1.get();
    var refNo2 = future2.get();

    assertEquals(1, Math.abs(Integer.parseInt(refNo2.substring(refNo2.length() - 5, refNo2.length() - 3)) -
        Integer.parseInt(refNo1.substring(refNo1.length() - 5, refNo1.length() - 3))));
    assertEquals(refNo2.substring(refNo2.length() - 3), refNo1.substring(refNo1.length() - 3));


    executorService.shutdown();
  }

  @Configuration
  public static class TestConfig {
    @Bean
    public ReferenceNumberGenerator generator() {
      return new ReferenceNumberGenerator(new HotelRegulationConfig());
    }
  }

}