package com.punjab.saga;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED, staticName = "identity")
public class OrchestrationData<T> implements Data<T> {

    private T value;
    private boolean previousStepSuccessful;

    public static <T> OrchestrationData<T> firstStep(T t) {
        return new OrchestrationData<>(t, true);
    }

    public static <T> OrchestrationData<T> failedStep(T t) {
        return new OrchestrationData<>(t, false);
    }

    public static <T> OrchestrationData<T> successStep(T t) {
        return new OrchestrationData<>(t, true);
    }
}
