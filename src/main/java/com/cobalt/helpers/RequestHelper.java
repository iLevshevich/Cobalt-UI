package com.cobalt.helpers;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component
public class RequestHelper {
    private ExecutorService executor = null;

    @Value("${cobalt.client.host}")
    private String cobaltClientHost;

    @Value("${cobalt.client.port}")
    private Integer cobaltClientPort;

    @Value("${cobalt.tcp.writer.charset}")
    private String cobaltWriterCharset;

    @Value("${cobalt.tcp.reader.charset}")
    private String cobaltReaderCharset;

    @Value("${cobalt.tcp.reader.buffer.length}")
    private Integer cobaltReaderBufferLength;

    @PostConstruct
    public void init() {
        if (Objects.isNull(executor)) {
            final Runtime runtime = Runtime.getRuntime();
            Objects.requireNonNull(runtime);

            final Integer cores = Runtime.getRuntime().availableProcessors();
            Objects.requireNonNull(cores);

            executor = Executors.newFixedThreadPool(cores << 1);
        }
    }

    @PreDestroy
    public void dispose() {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
    }

    @SneakyThrows({IOException.class})
    public <T, R> R request(@NonNull T data,
                            @NonNull BiConsumer<T, DataOutputStream> write,
                            @NonNull Function<DataInputStream, R> read) {
        @Cleanup final Socket socket = new Socket(cobaltClientHost, cobaltClientPort);
        @Cleanup final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        @Cleanup final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        write.accept(data, outputStream);
        return read.apply(inputStream);
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class, TimeoutException.class})
    public void asyncWrite(@NonNull final String data,
                           @NonNull final DataOutputStream stream) {
        final Callable<Void> task = new AsyncWriter(data, stream);
        final Future<Void> write = executor.submit(task);
        {
            write.get(5, TimeUnit.MINUTES);
        }
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class, TimeoutException.class})
    public String asyncRead(@NonNull final DataInputStream stream) {
        final Callable<String> task = new AsyncReader(stream);
        final Future<String> read = executor.submit(task);
        {
            return read.get(5, TimeUnit.MINUTES);
        }
    }

    private class AsyncWriter implements Callable<Void> {
        private final String data;
        private final DataOutputStream stream;

        AsyncWriter(@NonNull final String data,
                    @NonNull final DataOutputStream stream) {
            this.data = data;
            this.stream = stream;
        }

        @Override
        public Void call() throws Exception {
            final byte[] bytes = data.getBytes(cobaltWriterCharset);
            {
                stream.write(bytes);
                stream.flush();
            }
            return null;
        }
    }

    private class AsyncReader implements Callable<String> {
        private final DataInputStream stream;

        AsyncReader(@NonNull final DataInputStream stream) {
            this.stream = stream;
        }

        @Override
        public String call() throws Exception {
            final byte[] buffer = new byte[cobaltReaderBufferLength];
            {
                final StringBuilder sb = new StringBuilder();
                int length = 0;
                while ((length = stream.read(buffer, 0, cobaltReaderBufferLength)) != -1) {
                    sb.append(new String(buffer, 0, length, cobaltReaderCharset));
                }
                return sb.toString();
            }
        }
    }
}
