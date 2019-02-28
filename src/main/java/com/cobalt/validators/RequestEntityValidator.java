package com.cobalt.validators;

import com.cobalt.annotations.RequestEntityValidate;
import com.cobalt.model.RequestEntity;
import com.cobalt.model.RequestEntityType;
import com.cobalt.validators.chains.IValidateChain;
import com.cobalt.validators.chains.ValidateChain;
import lombok.NonNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class RequestEntityValidator implements ConstraintValidator<RequestEntityValidate, RequestEntity> {
    private static final IValidateChain VALIDATOR =
            new ValidateChain(RequestEntityValidator::TcpToAmqpValidator,
                    new ValidateChain(RequestEntityValidator::AmqpToAmqpValidator,
                            new ValidateChain(RequestEntityValidator::TcpToTcpValidator,
                                    new ValidateChain(RequestEntityValidator::AmqpToTcpValidator, null))));

    @Override
    public boolean isValid(@NonNull final RequestEntity value,
                           @NonNull final ConstraintValidatorContext context) {
        return VALIDATOR.validate(value);
    }

    private static Boolean TcpToAmqpValidator(@NonNull final RequestEntity entity,
                                              final IValidateChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.TcpToAmqp)) {
            boolean valid = true;
            {
                valid &= Objects.nonNull(entity.getId());
                valid &= Objects.nonNull(entity.getDescription());

                valid &= Objects.nonNull(entity.getInputHost());
                valid &= Objects.nonNull(entity.getInputPort());

                valid &= Objects.nonNull(entity.getOutputHost());
                valid &= Objects.nonNull(entity.getOutputPort());
                valid &= Objects.nonNull(entity.getOutputUser());
                valid &= Objects.nonNull(entity.getOutputPassword());
                valid &= Objects.nonNull(entity.getOutputVhost());
                valid &= Objects.nonNull(entity.getOutputPrefetch());
                valid &= Objects.nonNull(entity.getOutputExchange());
                valid &= Objects.nonNull(entity.getOutputQueue());
                valid &= Objects.nonNull(entity.getOutputRoutingKey());
            }

            if (valid) {
                valid &= !entity.getId().isEmpty();
                valid &= !entity.getDescription().isEmpty();

                valid &= !entity.getInputHost().isEmpty();
                valid &= !entity.getInputPort().isEmpty();

                valid &= !entity.getOutputHost().isEmpty();
                valid &= !entity.getOutputPort().isEmpty();
                valid &= !entity.getOutputUser().isEmpty();
                valid &= !entity.getOutputPassword().isEmpty();
                valid &= !entity.getOutputVhost().isEmpty();
                valid &= !entity.getOutputPrefetch().isEmpty();
                valid &= !entity.getOutputExchange().isEmpty();
                valid &= !entity.getOutputQueue().isEmpty();
                valid &= !entity.getOutputRoutingKey().isEmpty();
            }
            return valid;
        } else if (Objects.nonNull(next)) {
            return next.validate(entity);
        }

        return false;
    }

    private static Boolean AmqpToAmqpValidator(@NonNull final RequestEntity entity,
                                               final IValidateChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.AmqpToAmqp)) {
            boolean valid = true;
            {
                valid &= Objects.nonNull(entity.getId());
                valid &= Objects.nonNull(entity.getDescription());

                valid &= Objects.nonNull(entity.getInputHost());
                valid &= Objects.nonNull(entity.getInputPort());
                valid &= Objects.nonNull(entity.getInputUser());
                valid &= Objects.nonNull(entity.getInputPassword());
                valid &= Objects.nonNull(entity.getInputVhost());
                valid &= Objects.nonNull(entity.getInputPrefetch());
                valid &= Objects.nonNull(entity.getInputExchange());
                valid &= Objects.nonNull(entity.getInputQueue());
                valid &= Objects.nonNull(entity.getInputRoutingKey());

                valid &= Objects.nonNull(entity.getOutputHost());
                valid &= Objects.nonNull(entity.getOutputPort());
                valid &= Objects.nonNull(entity.getOutputUser());
                valid &= Objects.nonNull(entity.getOutputPassword());
                valid &= Objects.nonNull(entity.getOutputVhost());
                valid &= Objects.nonNull(entity.getOutputPrefetch());
                valid &= Objects.nonNull(entity.getOutputExchange());
                valid &= Objects.nonNull(entity.getOutputQueue());
                valid &= Objects.nonNull(entity.getOutputRoutingKey());
            }

            if (valid) {
                valid &= !entity.getId().isEmpty();
                valid &= !entity.getDescription().isEmpty();

                valid &= !entity.getInputHost().isEmpty();
                valid &= !entity.getInputPort().isEmpty();
                valid &= !entity.getInputUser().isEmpty();
                valid &= !entity.getInputPassword().isEmpty();
                valid &= !entity.getInputVhost().isEmpty();
                valid &= !entity.getInputPrefetch().isEmpty();
                valid &= !entity.getInputExchange().isEmpty();
                valid &= !entity.getInputQueue().isEmpty();
                valid &= !entity.getInputRoutingKey().isEmpty();

                valid &= !entity.getOutputHost().isEmpty();
                valid &= !entity.getOutputPort().isEmpty();
                valid &= !entity.getOutputUser().isEmpty();
                valid &= !entity.getOutputPassword().isEmpty();
                valid &= !entity.getOutputVhost().isEmpty();
                valid &= !entity.getOutputPrefetch().isEmpty();
                valid &= !entity.getOutputExchange().isEmpty();
                valid &= !entity.getOutputQueue().isEmpty();
                valid &= !entity.getOutputRoutingKey().isEmpty();
            }
            return valid;
        } else if (Objects.nonNull(next)) {
            return next.validate(entity);
        }

        return false;
    }

    private static Boolean TcpToTcpValidator(@NonNull final RequestEntity entity,
                                             final IValidateChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.TcpToTcp)) {
            boolean valid = true;
            {
                valid &= Objects.nonNull(entity.getId());
                valid &= Objects.nonNull(entity.getDescription());

                valid &= Objects.nonNull(entity.getInputHost());
                valid &= Objects.nonNull(entity.getInputPort());

                valid &= Objects.nonNull(entity.getOutputHost());
                valid &= Objects.nonNull(entity.getOutputPort());
            }

            if (valid) {
                valid &= !entity.getId().isEmpty();
                valid &= !entity.getDescription().isEmpty();

                valid &= !entity.getInputHost().isEmpty();
                valid &= !entity.getInputPort().isEmpty();

                valid &= !entity.getOutputHost().isEmpty();
                valid &= !entity.getOutputPort().isEmpty();
            }
            return valid;
        } else if (Objects.nonNull(next)) {
            return next.validate(entity);
        }

        return false;
    }

    private static Boolean AmqpToTcpValidator(@NonNull final RequestEntity entity,
                                              final IValidateChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.AmqpToTcp)) {
            boolean valid = true;
            {
                valid &= Objects.nonNull(entity.getId());
                valid &= Objects.nonNull(entity.getDescription());

                valid &= Objects.nonNull(entity.getInputHost());
                valid &= Objects.nonNull(entity.getInputPort());
                valid &= Objects.nonNull(entity.getInputUser());
                valid &= Objects.nonNull(entity.getInputPassword());
                valid &= Objects.nonNull(entity.getInputVhost());
                valid &= Objects.nonNull(entity.getInputPrefetch());
                valid &= Objects.nonNull(entity.getInputExchange());
                valid &= Objects.nonNull(entity.getInputQueue());
                valid &= Objects.nonNull(entity.getInputRoutingKey());

                valid &= Objects.nonNull(entity.getOutputHost());
                valid &= Objects.nonNull(entity.getOutputPort());
            }

            if (valid) {
                valid &= !entity.getId().isEmpty();
                valid &= !entity.getDescription().isEmpty();

                valid &= !entity.getInputHost().isEmpty();
                valid &= !entity.getInputPort().isEmpty();
                valid &= !entity.getInputUser().isEmpty();
                valid &= !entity.getInputPassword().isEmpty();
                valid &= !entity.getInputVhost().isEmpty();
                valid &= !entity.getInputPrefetch().isEmpty();
                valid &= !entity.getInputExchange().isEmpty();
                valid &= !entity.getInputQueue().isEmpty();
                valid &= !entity.getInputRoutingKey().isEmpty();

                valid &= !entity.getOutputHost().isEmpty();
                valid &= !entity.getOutputPort().isEmpty();
            }
            return valid;
        } else if (Objects.nonNull(next)) {
            return next.validate(entity);
        }

        return false;
    }
}
