package com.cobalt.helpers.chains;

import com.cobalt.model.RequestEntity;

import java.util.Map;

public interface IBuildChain {
    RequestEntity build(final Map<String, Object> map);
}
