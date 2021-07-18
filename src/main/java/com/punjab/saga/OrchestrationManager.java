package com.punjab.saga;

import java.util.Objects;

@FunctionalInterface
public interface OrchestrationManager<T extends Data, R extends Data> {

    R apply(T t);

    default OrchestrationManager<T, R> first(OrchestrationManager<T, R> participant) {
        Objects.nonNull(participant);
        return participant;
    }

    default <V extends Data> OrchestrationManager<T, V> andThen(OrchestrationManager<? super R, ? extends V> participant) {
        Objects.nonNull(participant);
        return (T t) -> t.isPreviousStepSuccessful() ? participant.apply(apply(t)) : passThrough(t);
    }

    default <V extends Data> OrchestrationManager<T, V> withCompensation(OrchestrationManager<? super R, ? extends V> participant) {
        Objects.nonNull(participant);
        return (T t) -> t.isPreviousStepSuccessful() ? passThrough(t) : participant.apply(apply(t));
    }

    default <V extends Data> V passThrough(T t) {
        return (V) t;
    }
}
