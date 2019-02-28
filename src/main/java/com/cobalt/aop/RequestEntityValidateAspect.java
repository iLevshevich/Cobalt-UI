package com.cobalt.aop;

import com.cobalt.exceptions.BadRequestException;
import com.cobalt.model.RequestEntity;
import lombok.Cleanup;
import lombok.NonNull;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.*;
import java.util.Objects;
import java.util.Set;

@Component
@Aspect
public class RequestEntityValidateAspect {
    private Validator validator;

    @PostConstruct
    public void init() {
        @Cleanup final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final ValidatorContext context = factory.usingContext();
        this.validator = context.getValidator();
    }

    @AfterReturning(value = "execution(com.cobalt.model.RequestEntity com.cobalt.helpers.ConverterHelper.convert(java.util.Map, ..)))", returning = "ret")
    public void validate(@NonNull final RequestEntity ret) {
        final Set<ConstraintViolation<RequestEntity>> validates = validator.validate(ret);
        Objects.requireNonNull(validates);

        if (validates.size() > 0) {
            throw new BadRequestException("Invalid objects value");
        }
    }
}
