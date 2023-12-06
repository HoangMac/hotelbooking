package com.assessment.hotelbooking.service.factory;

public interface EnumBeanFactory<E extends Enum<E>, I> {

  I getInstance(E e);
}
