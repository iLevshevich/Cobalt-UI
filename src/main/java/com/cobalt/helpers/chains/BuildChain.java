package com.cobalt.helpers.chains;

import com.cobalt.model.RequestEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

import java.util.Map;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class BuildChain implements IBuildChain {
    private final BiFunction<Map<String, Object>, IBuildChain, RequestEntity> executor;
    private final IBuildChain next;

    @Synchronized
    public RequestEntity build(@NonNull final Map<String, Object> map) {
        return executor.apply(map, next);
    }
}
