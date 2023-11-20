package com.example.backendtaskempik.config;

import com.example.backendtaskempik.model.CustomUserFilter;
import com.example.backendtaskempik.util.JsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private final static SimpleFilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("customUserFilter", new CustomUserFilter());

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module()) // Support Optional<T> properly
                .registerModule(new JavaTimeModule()) // Support LocalDate
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setFilterProvider(filterProvider);
    }

    // Make printing the Json beautifully indented
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public JsonTransformer jsonTransformer(ObjectMapper objectMapper) {
        return new JsonTransformer(objectMapper);
    }
}
