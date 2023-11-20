package com.example.backendtaskempik.model;

import com.example.backendtaskempik.UserAbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest extends UserAbstractTest {

    @Test
    public void userWithCalculationsUnitTest() {
        // given
        User user = getTestUser();

        // when
        User userWithCalculations = user.withCalculations();

        // then
        assertEquals(6.0 / user.followers() * (2 + user.publicRepos()), userWithCalculations.calculations());
    }
}
