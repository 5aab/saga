package com.punjab.saga;

public interface Data<T> {

    boolean isPreviousStepSuccessful();

    T getValue();
}
