package com.cobalt.aop;

import com.cobalt.annotations.IsAvailable;
import com.cobalt.exceptions.ForbiddenException;
import com.cobalt.helpers.UserHelper;
import com.cobalt.helpers.xml.entities.Grant;
import com.cobalt.helpers.xml.entities.Grants;
import com.cobalt.helpers.xml.entities.User;
import com.cobalt.helpers.xml.entities.Users;
import lombok.NonNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Component
@Aspect
public class SecurityOperationsAspect {
    private static final String ALLOW_OPERATION = "allow";

    private final UserHelper helper;

    @Autowired
    public SecurityOperationsAspect(@NonNull final UserHelper helper) {
        this.helper = helper;
    }

    @Around("@annotation(com.cobalt.annotations.IsAvailable)")
    public Object available(@NonNull final ProceedingJoinPoint point) throws Throwable {
        {
            checkAccess(point);
        }
        return point.proceed();
    }

    private void checkAccess(@NonNull final ProceedingJoinPoint point) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Objects.requireNonNull(auth);

        final MethodSignature signature = (MethodSignature) point.getSignature();
        Objects.requireNonNull(signature);

        final Method method = signature.getMethod();
        Objects.requireNonNull(method);

        final IsAvailable annotation = method.getAnnotation(IsAvailable.class);
        Objects.requireNonNull(annotation);

        final String name = auth.getName();
        final String operation = annotation.value();

        if (!isOperationAvailableByName(name, operation)) {
            final String message = String.format("Unauthorized access attempt: %s -> %s", name, operation);
            throw new ForbiddenException(message);
        }
    }

    private Boolean isOperationAvailableByName(@NonNull String name,
                                               @NonNull String operation) {
        final Users users = helper.getUsers();
        Objects.requireNonNull(users);

        final List<User> userList = users.getUser();
        Objects.requireNonNull(userList);

        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .map(user -> {
                    final Grants grants = user.getGrants();
                    Objects.requireNonNull(grants);

                    final List<Grant> grantList = grants.getGrant();
                    Objects.requireNonNull(grantList);

                    final Grant operationGrant = grantList.stream()
                            .filter(grant -> grant.getOperation().equals(operation))
                            .findFirst()
                            .orElse(null);
                    Objects.requireNonNull(operationGrant);

                    return operationGrant.getAvailable().equals(ALLOW_OPERATION);
                })
                .findFirst()
                .orElse(false);
    }
}
