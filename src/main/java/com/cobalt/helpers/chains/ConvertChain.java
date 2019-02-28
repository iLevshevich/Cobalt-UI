package com.cobalt.helpers.chains;

import com.cobalt.functions.TriFunction;
import com.cobalt.model.RequestEntity;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
public class ConvertChain implements IConvertChain {
    private final TriFunction<RequestEntity, JsonNode, IConvertChain, String> executor;
    private final IConvertChain next;

    @Synchronized
    public String convert(@NonNull final RequestEntity entity,
                          @NonNull final JsonNode signature) {
        return executor.apply(entity, signature, next);
    }
}
