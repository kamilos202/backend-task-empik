package com.example.backendtaskempik.config;

import com.example.backendtaskempik.model.CustomUserFilter;
import com.example.backendtaskempik.util.JsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Configuration class for setting up beans related to JSON serialization and transformation.
 */
@Configuration
public class AppConfiguration {

    private final static SimpleFilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("customUserFilter", new CustomUserFilter());

    /**
     * Configure and provide an ObjectMapper bean with support for Optional and LocalDate.
     *
     * @return Configured ObjectMapper bean.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new Jdk8Module()) // Support Optional<T> properly
                .registerModule(new JavaTimeModule()) // Support LocalDate
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setFilterProvider(filterProvider);
    }

    /**
     * Configure and provide a MappingJackson2HttpMessageConverter bean using the provided ObjectMapper.
     * This makes printing the Json beautifully indented.
     *
     * @param objectMapper The configured ObjectMapper.
     * @return Configured MappingJackson2HttpMessageConverter bean.
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    /**
     * Configure and provide a JsonTransformer bean using the provided ObjectMapper.
     *
     * @param objectMapper The configured ObjectMapper.
     * @return Configured JsonTransformer bean.
     */
    @Bean
    public JsonTransformer jsonTransformer(ObjectMapper objectMapper) {
        return new JsonTransformer(objectMapper);
    }
}
