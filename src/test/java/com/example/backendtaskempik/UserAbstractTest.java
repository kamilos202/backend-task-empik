package com.example.backendtaskempik;

import com.example.backendtaskempik.model.User;

import java.time.LocalDateTime;

public class UserAbstractTest {

    protected static User getTestUser() {
        return new User(1L,
                "user123",
                "testUserName",
                "USER",
                "www.test.com", LocalDateTime.MIN,
                null,
                1000,10);
    }
}
