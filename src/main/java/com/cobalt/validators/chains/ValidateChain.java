package com.cobalt.validators.chains;

import com.cobalt.model.RequestEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class ValidateChain implements IValidateChain {
    private final BiFunction<RequestEntity, IValidateChain, Boolean> executor;
    private final IValidateChain next;

    @Synchronized
    public Boolean validate(@NonNull final RequestEntity entity) {
        return executor.apply(entity, next);
    }
}
