package com.cobalt.helpers;

import com.cobalt.misc.Pair;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class JsonHelper {
    @SneakyThrows({IOException.class})
    public JsonNode toJson(@NonNull final String content) {
        final ObjectMapper mapper = new ObjectMapper();
        Objects.requireNonNull(mapper);

        final JsonNode result = mapper.readValue(content, JsonNode.class);
        Objects.requireNonNull(result);

        return result;
    }

    public String toString(@NonNull final JsonNode content) {
        return content.toString();
    }

    public String asText(@NonNull final JsonNode content) {
        return content.asText();
    }

    public JsonNode get(@NonNull final JsonNode node,
                        @NonNull final String key) {
        return ((ObjectNode) node).get(key);
    }

    public void put(@NonNull final JsonNode node,
                    @NonNull final String key,
                    @NonNull final String value) {
        ((ObjectNode) node).put(key, value);
    }

    public void put(@NonNull final JsonNode node,
                    @NonNull final String key,
                    @NonNull final JsonNode value) {
        ((ObjectNode) node).set(key, value);
    }

    public void put(@NonNull final JsonNode node,
                    @NonNull final String key,
                    @NonNull final ArrayNode value) {
        ((ObjectNode) node).set(key, value);
    }

    public void put(@NonNull final ArrayNode node,
                    @NonNull final JsonNode value) {
        node.add(value);
    }

    public void put(@NonNull final ArrayNode node,
                    @NonNull final String value) {
        node.add(value);
    }

    public void remove(@NonNull final JsonNode node,
                       @NonNull final String key) {
        ((ObjectNode) node).remove(key);
    }

    public void removeIsExist(@NonNull final JsonNode node,
                              @NonNull final String key) {
        if (isExist(node, key)) {
            remove(node, key);
        }
    }

    public Boolean isExist(@NonNull final JsonNode node,
                           @NonNull final String key) {
        return node.has(key);
    }

    public JsonNode getInstance() {
        final ObjectMapper mapper = new ObjectMapper();
        Objects.requireNonNull(mapper);

        final JsonNode result = mapper.createObjectNode();
        Objects.requireNonNull(result);

        return result;
    }

    public ArrayNode getArrayInstance() {
        final ObjectMapper mapper = new ObjectMapper();
        Objects.requireNonNull(mapper);

        final ArrayNode result = mapper.createArrayNode();
        Objects.requireNonNull(result);

        return result;
    }

    @SafeVarargs
    public final JsonNode getInstance(@NonNull final Pair<String, String>... pairs) {
        final ObjectMapper mapper = new ObjectMapper();
        Objects.requireNonNull(mapper);

        final JsonNode result = mapper.createObjectNode();
        Objects.requireNonNull(result);

        for (final Pair<String, String> pair : pairs) {
            put(result, pair.getKey(), pair.getValue());
        }
        return result;
    }

    public final JsonNode getInstance(@NonNull final String key,
                                      @NonNull final ArrayNode value) {
        final ObjectMapper mapper = new ObjectMapper();
        Objects.requireNonNull(mapper);

        final JsonNode result = mapper.createObjectNode();
        Objects.requireNonNull(result);
        {
            put(result, key, value);
        }
        return result;
    }
}
