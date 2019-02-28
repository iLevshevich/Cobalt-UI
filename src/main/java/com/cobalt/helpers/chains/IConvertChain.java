package com.cobalt.helpers.chains;

import com.cobalt.model.RequestEntity;
import com.fasterxml.jackson.databind.JsonNode;

public interface IConvertChain {
    String convert(final RequestEntity entity, final JsonNode signature);
}