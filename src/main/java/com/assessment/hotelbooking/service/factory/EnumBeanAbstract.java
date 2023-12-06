package com.assessment.hotelbooking.service.factory;

import java.util.EnumMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class EnumBeanAbstract<E extends Enum<E>, I> implements EnumBeanFactory<E, I> {

  protected EnumMap<E, I> enumMap;

  @Autowired
  protected ApplicationContext applicationContext;

  @Override
  public I getInstance(E e) {
    return enumMap.get(e);
  }
}
