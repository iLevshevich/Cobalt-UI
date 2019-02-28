package com.cobalt.validators.chains;

import com.cobalt.model.RequestEntity;

public interface IValidateChain {
    Boolean validate(final RequestEntity entity);
}
