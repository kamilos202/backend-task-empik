package com.example.backendtaskempik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Request {

    @Id
    private String login;
    private Long requestCount;

    public Request() {
    }

    public Request(String login, Long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

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
