package com.example.backendtaskempik.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.Arrays;

public class CustomUserFilter extends SimpleBeanPropertyFilter {

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        // Include only fields with @JsonView(Views.Public.class) annotation
        return hasJsonViewAnnotation(writer);
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        // Include only fields with @JsonView(Views.Public.class) annotation
        return hasJsonViewAnnotation(writer);
    }

    private boolean hasJsonViewAnnotation(PropertyWriter writer) {
        JsonView annotation = writer.getAnnotation(JsonView.class);
        return annotation != null && Arrays.asList(annotation.value()).contains(Views.Public.class);
    }
}
