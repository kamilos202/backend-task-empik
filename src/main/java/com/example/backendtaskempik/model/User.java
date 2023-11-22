package com.example.backendtaskempik.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

/**
 * Record representing a User with specific fields for serialization.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonFilter("customUserFilter")
public record User(
        @JsonView(Views.Public.class) Long id,
        @JsonView(Views.Public.class) String login,
        @JsonView(Views.Public.class) String name,
        @JsonView(Views.Public.class) String type,
        @JsonView(Views.Public.class) String avatarUrl,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        @JsonView(Views.Public.class) LocalDateTime createdAt,
        @JsonView(Views.Public.class) Double calculations,
        Integer followers,
        Integer publicRepos) {

    /**
     * Calculate and return User with additional calculations field.
     *
     * @return User with calculations.
     */
    public User withCalculations() {
        double calculations = 0.0;
        if (followers() != 0) {
            calculations = (double) 6 / followers() * (2 + publicRepos());
        }
        return new User(id(), login(), name(), type(), avatarUrl(), createdAt(),
                calculations, followers(), publicRepos());
    }
}