package com.cobalt.helpers;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.*;

@Component
public class ShutdownHelper {
    public static final Long TIMEOUT = 3L;

    private ExecutorService executor = null;
    private final ConfigurableApplicationContext context;

    @Autowired
    public ShutdownHelper(@NonNull final ConfigurableApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void init() {
        if (Objects.isNull(executor)) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    @PreDestroy
    public void dispose() {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
    }

    public void asyncShutdown() {
        executor.execute(() -> {
            sleep(TIMEOUT);
            System.exit(SpringApplication.exit(context, () -> 0));
        });
    }

    @SneakyThrows({InterruptedException.class})
    private void sleep(@NonNull final Long timeout) {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
