package com.example.backendtaskempik.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.Arrays;

/**
 * Custom JSON filter for user serialization based on @JsonView annotations.
 */
public class CustomUserFilter extends SimpleBeanPropertyFilter {

    /**
     * Include only fields with @JsonView(Views.Public.class) annotation during deserialization.
     *
     * @param writer The BeanPropertyWriter.
     * @return True if the field should be included, false otherwise.
     */
    @Override
    protected boolean include(BeanPropertyWriter writer) {
        // Include only fields with @JsonView(Views.Public.class) annotation
        return hasJsonViewAnnotation(writer);
    }

    /**
     * Include only fields with @JsonView(Views.Public.class) annotation during deserialization.
     *
     * @param writer The PropertyWriter.
     * @return True if the field should be included, false otherwise.
     */
    @Override
    protected boolean include(PropertyWriter writer) {
        // Include only fields with @JsonView(Views.Public.class) annotation
        return hasJsonViewAnnotation(writer);
    }

    /**
     * Check if the field has @JsonView(Views.Public.class) annotation.
     *
     * @param writer The PropertyWriter.
     * @return True if the field has the required annotation, false otherwise.
     */
    private boolean hasJsonViewAnnotation(PropertyWriter writer) {
        JsonView annotation = writer.getAnnotation(JsonView.class);
        return annotation != null && Arrays.asList(annotation.value()).contains(Views.Public.class);
    }
}
