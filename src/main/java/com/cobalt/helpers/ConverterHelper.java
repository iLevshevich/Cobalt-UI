package com.cobalt.helpers;

import com.cobalt.helpers.chains.BuildChain;
import com.cobalt.helpers.chains.ConvertChain;
import com.cobalt.helpers.chains.IBuildChain;
import com.cobalt.helpers.chains.IConvertChain;
import com.cobalt.model.RequestEntity;
import com.cobalt.model.RequestEntityType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class ConverterHelper {
    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String DESCRIPTION_FIELD = "__description";
    private static final String TYPE_FIELD = "__type";
    private static final String INPUT_HOST_FIELD = "__input_host";
    private static final String INPUT_PORT_FIELD = "__input_port";
    private static final String INPUT_USER_FIELD = "__input_user";
    private static final String INPUT_PASSWORD_FIELD = "__input_password";
    private static final String INPUT_VHOST_FIELD = "__input_vhost";
    private static final String INPUT_PREFETCH_FIELD = "__input_prefetch";
    private static final String INPUT_EXCHANGE_FIELD = "__input_exchange";
    private static final String INPUT_QUEUE_FIELD = "__input_queue";
    private static final String INPUT_ROUTING_KEY_FIELD = "__input_routing_key";
    private static final String OUTPUT_HOST_FIELD = "__output_host";
    private static final String OUTPUT_PORT_FIELD = "__output_port";
    private static final String OUTPUT_USER_FIELD = "__output_user";
    private static final String OUTPUT_PASSWORD_FIELD = "__output_password";
    private static final String OUTPUT_VHOST_FIELD = "__output_vhost";
    private static final String OUTPUT_PREFETCH_FIELD = "__output_prefetch";
    private static final String OUTPUT_EXCHANGE_FIELD = "__output_exchange";
    private static final String OUTPUT_QUEUE_FIELD = "__output_queue";
    private static final String OUTPUT_ROUTING_KEY_FIELD = "__output_routing_key";

    private final IConvertChain converter =
            new ConvertChain(this::TcpToAmqpConverter,
                    new ConvertChain(this::AmqpToAmqpConverter,
                            new ConvertChain(this::TcpToTcpConverter,
                                    new ConvertChain(this::AmqpToTcpConverter, null))));

    private final IBuildChain builder =
            new BuildChain(this::TcpToAmqpBuilder,
                    new BuildChain(this::AmqpToAmqpBuilder,
                            new BuildChain(this::TcpToTcpBuilder,
                                    new BuildChain(this::AmqpToTcpBuilder, null))));

    private final JsonHelper jsonHelper;

    @Autowired
    public ConverterHelper(@NonNull final JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public String convert(@NonNull final RequestEntity entity,
                          @NonNull final JsonNode signature) {
        return converter.convert(entity, signature);
    }

    public RequestEntity convert(@NonNull final Map<String, Object> map) {
        return builder.build(map);
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private RequestEntity TcpToAmqpBuilder(@NonNull final Map<String, Object> map, final IBuildChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING));
        if (type.equals(RequestEntityType.TcpToAmqp)) {
            return RequestEntity.builder()
                    .id(UUID.randomUUID().toString().toUpperCase())
                    .description(URLDecoder.decode(map.get(DESCRIPTION_FIELD).toString(), CHARACTER_ENCODING))
                    .type(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputHost(URLDecoder.decode(map.get(INPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPort(URLDecoder.decode(map.get(INPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .outputHost(URLDecoder.decode(map.get(OUTPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPort(URLDecoder.decode(map.get(OUTPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .outputUser(URLDecoder.decode(map.get(OUTPUT_USER_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPassword(URLDecoder.decode(map.get(OUTPUT_PASSWORD_FIELD).toString(), CHARACTER_ENCODING))
                    .outputVhost(URLDecoder.decode(map.get(OUTPUT_VHOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPrefetch(URLDecoder.decode(map.get(OUTPUT_PREFETCH_FIELD).toString(), CHARACTER_ENCODING))
                    .outputExchange(URLDecoder.decode(map.get(OUTPUT_EXCHANGE_FIELD).toString(), CHARACTER_ENCODING))
                    .outputQueue(URLDecoder.decode(map.get(OUTPUT_QUEUE_FIELD).toString(), CHARACTER_ENCODING))
                    .outputRoutingKey(URLDecoder.decode(map.get(OUTPUT_ROUTING_KEY_FIELD).toString(), CHARACTER_ENCODING))
                    .build();
        } else if (Objects.nonNull(next)) {
            return next.build(map);
        }
        return null;
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private RequestEntity AmqpToAmqpBuilder(@NonNull final Map<String, Object> map, final IBuildChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING));
        if (type.equals(RequestEntityType.AmqpToAmqp)) {
            return RequestEntity.builder()
                    .id(UUID.randomUUID().toString().toUpperCase())
                    .description(URLDecoder.decode(map.get(DESCRIPTION_FIELD).toString(), CHARACTER_ENCODING))
                    .type(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputHost(URLDecoder.decode(map.get(INPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPort(URLDecoder.decode(map.get(INPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .inputUser(URLDecoder.decode(map.get(INPUT_USER_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPassword(URLDecoder.decode(map.get(INPUT_PASSWORD_FIELD).toString(), CHARACTER_ENCODING))
                    .inputVhost(URLDecoder.decode(map.get(INPUT_VHOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPrefetch(URLDecoder.decode(map.get(INPUT_PREFETCH_FIELD).toString(), CHARACTER_ENCODING))
                    .inputExchange(URLDecoder.decode(map.get(INPUT_EXCHANGE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputQueue(URLDecoder.decode(map.get(INPUT_QUEUE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputRoutingKey(URLDecoder.decode(map.get(INPUT_ROUTING_KEY_FIELD).toString(), CHARACTER_ENCODING))
                    .outputHost(URLDecoder.decode(map.get(OUTPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPort(URLDecoder.decode(map.get(OUTPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .outputUser(URLDecoder.decode(map.get(OUTPUT_USER_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPassword(URLDecoder.decode(map.get(OUTPUT_PASSWORD_FIELD).toString(), CHARACTER_ENCODING))
                    .outputVhost(URLDecoder.decode(map.get(OUTPUT_VHOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPrefetch(URLDecoder.decode(map.get(OUTPUT_PREFETCH_FIELD).toString(), CHARACTER_ENCODING))
                    .outputExchange(URLDecoder.decode(map.get(OUTPUT_EXCHANGE_FIELD).toString(), CHARACTER_ENCODING))
                    .outputQueue(URLDecoder.decode(map.get(OUTPUT_QUEUE_FIELD).toString(), CHARACTER_ENCODING))
                    .outputRoutingKey(URLDecoder.decode(map.get(OUTPUT_ROUTING_KEY_FIELD).toString(), CHARACTER_ENCODING))
                    .build();
        } else if (Objects.nonNull(next)) {
            return next.build(map);
        }
        return null;
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private RequestEntity TcpToTcpBuilder(@NonNull final Map<String, Object> map, final IBuildChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING));
        if (type.equals(RequestEntityType.TcpToTcp)) {
            return RequestEntity.builder()
                    .id(UUID.randomUUID().toString().toUpperCase())
                    .description(URLDecoder.decode(map.get(DESCRIPTION_FIELD).toString(), CHARACTER_ENCODING))
                    .type(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputHost(URLDecoder.decode(map.get(INPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPort(URLDecoder.decode(map.get(INPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .outputHost(URLDecoder.decode(map.get(OUTPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPort(URLDecoder.decode(map.get(OUTPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .build();
        } else if (Objects.nonNull(next)) {
            return next.build(map);
        }
        return null;
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private RequestEntity AmqpToTcpBuilder(@NonNull final Map<String, Object> map, final IBuildChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING));
        if (type.equals(RequestEntityType.AmqpToTcp)) {
            return RequestEntity.builder()
                    .id(UUID.randomUUID().toString().toUpperCase())
                    .description(URLDecoder.decode(map.get(DESCRIPTION_FIELD).toString(), CHARACTER_ENCODING))
                    .type(URLDecoder.decode(map.get(TYPE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputHost(URLDecoder.decode(map.get(INPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPort(URLDecoder.decode(map.get(INPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .inputUser(URLDecoder.decode(map.get(INPUT_USER_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPassword(URLDecoder.decode(map.get(INPUT_PASSWORD_FIELD).toString(), CHARACTER_ENCODING))
                    .inputVhost(URLDecoder.decode(map.get(INPUT_VHOST_FIELD).toString(), CHARACTER_ENCODING))
                    .inputPrefetch(URLDecoder.decode(map.get(INPUT_PREFETCH_FIELD).toString(), CHARACTER_ENCODING))
                    .inputExchange(URLDecoder.decode(map.get(INPUT_EXCHANGE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputQueue(URLDecoder.decode(map.get(INPUT_QUEUE_FIELD).toString(), CHARACTER_ENCODING))
                    .inputRoutingKey(URLDecoder.decode(map.get(INPUT_ROUTING_KEY_FIELD).toString(), CHARACTER_ENCODING))
                    .outputHost(URLDecoder.decode(map.get(OUTPUT_HOST_FIELD).toString(), CHARACTER_ENCODING))
                    .outputPort(URLDecoder.decode(map.get(OUTPUT_PORT_FIELD).toString(), CHARACTER_ENCODING))
                    .build();
        } else if (Objects.nonNull(next)) {
            return next.build(map);
        }
        return null;
    }

    private String TcpToAmqpConverter(@NonNull final RequestEntity entity,
                                      @NonNull final JsonNode signature,
                                      final IConvertChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.TcpToAmqp)) {
            final JsonNode dispatcher = jsonHelper.getInstance();
            Objects.requireNonNull(dispatcher);
            {
                jsonHelper.put(dispatcher, "id", entity.getId());
                jsonHelper.put(dispatcher, "description", entity.getDescription());
                jsonHelper.put(dispatcher, "type", entity.getType());

                final JsonNode input = jsonHelper.getInstance();
                Objects.requireNonNull(input);
                {
                    jsonHelper.put(input, "host", entity.getInputHost());
                    jsonHelper.put(input, "port", entity.getInputPort());
                }
                jsonHelper.put(dispatcher, "input", input);

                final JsonNode output = jsonHelper.getInstance();
                Objects.requireNonNull(output);
                {
                    jsonHelper.put(output, "host", entity.getOutputHost());
                    jsonHelper.put(output, "port", entity.getOutputPort());
                    jsonHelper.put(output, "user", entity.getOutputUser());
                    jsonHelper.put(output, "password", entity.getOutputPassword());
                    jsonHelper.put(output, "vhost", entity.getOutputVhost().replaceFirst("/", "\\/"));
                    jsonHelper.put(output, "prefetch", entity.getOutputPrefetch());
                    jsonHelper.put(output, "exchange", entity.getOutputExchange());
                    jsonHelper.put(output, "queue", entity.getOutputQueue());
                    jsonHelper.put(output, "routing_key", entity.getOutputRoutingKey());
                }
                jsonHelper.put(dispatcher, "output", output);
            }

            final ArrayNode dispatchers = jsonHelper.getArrayInstance();
            Objects.requireNonNull(dispatchers);
            {
                jsonHelper.put(dispatchers, dispatcher);
            }
            jsonHelper.put(signature, "dispatchers", dispatchers);

            return jsonHelper.toString(signature);
        } else if (Objects.nonNull(next)) {
            return next.convert(entity, signature);
        }
        return null;
    }

    private String AmqpToAmqpConverter(@NonNull final RequestEntity entity,
                                       @NonNull final JsonNode signature,
                                       final IConvertChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.AmqpToAmqp)) {
            final JsonNode dispatcher = jsonHelper.getInstance();
            Objects.requireNonNull(dispatcher);
            {
                jsonHelper.put(dispatcher, "id", entity.getId());
                jsonHelper.put(dispatcher, "description", entity.getDescription());
                jsonHelper.put(dispatcher, "type", entity.getType());

                final JsonNode input = jsonHelper.getInstance();
                Objects.requireNonNull(input);
                {
                    jsonHelper.put(input, "host", entity.getInputHost());
                    jsonHelper.put(input, "port", entity.getInputPort());
                    jsonHelper.put(input, "user", entity.getInputUser());
                    jsonHelper.put(input, "password", entity.getInputPassword());
                    jsonHelper.put(input, "vhost", entity.getInputVhost().replaceFirst("/", "\\/"));
                    jsonHelper.put(input, "prefetch", entity.getInputPrefetch());
                    jsonHelper.put(input, "exchange", entity.getInputExchange());
                    jsonHelper.put(input, "queue", entity.getInputQueue());
                    jsonHelper.put(input, "routing_key", entity.getInputRoutingKey());
                }
                jsonHelper.put(dispatcher, "input", input);

                final JsonNode output = jsonHelper.getInstance();
                Objects.requireNonNull(output);
                {
                    jsonHelper.put(output, "host", entity.getOutputHost());
                    jsonHelper.put(output, "port", entity.getOutputPort());
                    jsonHelper.put(output, "user", entity.getOutputUser());
                    jsonHelper.put(output, "password", entity.getOutputPassword());
                    jsonHelper.put(output, "vhost", entity.getOutputVhost().replaceFirst("/", "\\/"));
                    jsonHelper.put(output, "prefetch", entity.getOutputPrefetch());
                    jsonHelper.put(output, "exchange", entity.getOutputExchange());
                    jsonHelper.put(output, "queue", entity.getOutputQueue());
                    jsonHelper.put(output, "routing_key", entity.getOutputRoutingKey());
                }
                jsonHelper.put(dispatcher, "output", output);
            }

            final ArrayNode dispatchers = jsonHelper.getArrayInstance();
            Objects.requireNonNull(dispatchers);
            {
                jsonHelper.put(dispatchers, dispatcher);
            }
            jsonHelper.put(signature, "dispatchers", dispatchers);

            return jsonHelper.toString(signature);
        } else if (Objects.nonNull(next)) {
            return next.convert(entity, signature);
        }
        return null;
    }

    private String TcpToTcpConverter(@NonNull final RequestEntity entity,
                                     @NonNull final JsonNode signature,
                                     final IConvertChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.TcpToTcp)) {
            final JsonNode dispatcher = jsonHelper.getInstance();
            Objects.requireNonNull(dispatcher);
            {
                jsonHelper.put(dispatcher, "id", entity.getId());
                jsonHelper.put(dispatcher, "description", entity.getDescription());
                jsonHelper.put(dispatcher, "type", entity.getType());

                final JsonNode input = jsonHelper.getInstance();
                Objects.requireNonNull(input);
                {
                    jsonHelper.put(input, "host", entity.getInputHost());
                    jsonHelper.put(input, "port", entity.getInputPort());
                }
                jsonHelper.put(dispatcher, "input", input);

                final JsonNode output = jsonHelper.getInstance();
                Objects.requireNonNull(output);
                {
                    jsonHelper.put(output, "host", entity.getOutputHost());
                    jsonHelper.put(output, "port", entity.getOutputPort());
                }
                jsonHelper.put(dispatcher, "output", output);
            }

            final ArrayNode dispatchers = jsonHelper.getArrayInstance();
            Objects.requireNonNull(dispatchers);
            {
                jsonHelper.put(dispatchers, dispatcher);
            }
            jsonHelper.put(signature, "dispatchers", dispatchers);

            return jsonHelper.toString(signature);
        } else if (Objects.nonNull(next)) {
            return next.convert(entity, signature);
        }
        return null;
    }

    private String AmqpToTcpConverter(@NonNull final RequestEntity entity,
                                      @NonNull final JsonNode signature,
                                      final IConvertChain next) {
        final RequestEntityType type = RequestEntityType.valueOf(entity.getType());
        if (type.equals(RequestEntityType.AmqpToTcp)) {
            final JsonNode dispatcher = jsonHelper.getInstance();
            Objects.requireNonNull(dispatcher);
            {
                jsonHelper.put(dispatcher, "id", entity.getId());
                jsonHelper.put(dispatcher, "description", entity.getDescription());
                jsonHelper.put(dispatcher, "type", entity.getType());

                final JsonNode input = jsonHelper.getInstance();
                Objects.requireNonNull(input);
                {
                    jsonHelper.put(input, "host", entity.getInputHost());
                    jsonHelper.put(input, "port", entity.getInputPort());
                    jsonHelper.put(input, "user", entity.getInputUser());
                    jsonHelper.put(input, "password", entity.getInputPassword());
                    jsonHelper.put(input, "vhost", entity.getInputVhost().replaceFirst("/", "\\/"));
                    jsonHelper.put(input, "prefetch", entity.getInputPrefetch());
                    jsonHelper.put(input, "exchange", entity.getInputExchange());
                    jsonHelper.put(input, "queue", entity.getInputQueue());
                    jsonHelper.put(input, "routing_key", entity.getInputRoutingKey());
                }
                jsonHelper.put(dispatcher, "input", input);

                final JsonNode output = jsonHelper.getInstance();
                Objects.requireNonNull(output);
                {
                    jsonHelper.put(output, "host", entity.getOutputHost());
                    jsonHelper.put(output, "port", entity.getOutputPort());
                }
                jsonHelper.put(dispatcher, "output", output);
            }

            final ArrayNode dispatchers = jsonHelper.getArrayInstance();
            Objects.requireNonNull(dispatchers);
            {
                jsonHelper.put(dispatchers, dispatcher);
            }
            jsonHelper.put(signature, "dispatchers", dispatchers);

            return jsonHelper.toString(signature);
        } else if (Objects.nonNull(next)) {
            return next.convert(entity, signature);
        }
        return null;
    }
}
