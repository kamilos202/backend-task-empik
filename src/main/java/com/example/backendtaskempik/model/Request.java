package com.example.backendtaskempik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entity representing a Request with a user's login and requestCount fields.
 */
@Entity
public class Request {

    @Id
    private String login;
    private Long requestCount;

    /**
     * Default constructor required by JPA.
     */
    public Request() {
    }

    /**
     * Constructor to initialize login and requestCount.
     *
     * @param login        The user's login.
     * @param requestCount The request count.
     */
    public Request(String login, Long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    // Getters and setters

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }
}
