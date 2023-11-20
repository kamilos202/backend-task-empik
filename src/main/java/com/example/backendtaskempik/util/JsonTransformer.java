package com.example.backendtaskempik.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JsonTransformer {
    private static final Logger logger = LoggerFactory.getLogger(JsonTransformer.class);
    private final ObjectMapper objectMapper;

    public JsonTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> Optional<T> deserialize(String object, Class<T> valueType) {
        try {
            return Optional.of(objectMapper.readValue(object, valueType));
        } catch (JsonProcessingException e) {
            logger.error("Unable to deserialize object {}", object, e);
        }
        return Optional.empty();
    }

    // add more similar methods if needed
}
