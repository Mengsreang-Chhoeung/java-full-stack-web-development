package com.ms.spring_security_jwt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null)
            objectMapper = new ObjectMapper();

        return objectMapper;
    }

    // JsonNode is a base class that ObjectNode and ArrayNode extend. JsonNode represents any valid Json structure whereas ObjectNode and ArrayNode are particular implementations for objects (aka maps) and arrays, respectively.

    public static String toJson(Object obj) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(obj);
    }

    public static JsonNode toJsonNode(Object obj) {
        if (obj == null) return null;

        return getObjectMapper().valueToTree(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return getObjectMapper().readValue(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return getObjectMapper().readValue(json, typeReference);
    }
}
