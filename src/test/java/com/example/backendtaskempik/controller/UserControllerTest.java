package com.example.backendtaskempik.controller;

import com.example.backendtaskempik.UserAbstractTest;
import com.example.backendtaskempik.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends UserAbstractTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void getUserByLoginReturnsUserSuccess() throws Exception {
        // given
        var user = getTestUser();
        when(userService.getUserByLogin("user123")).thenReturn(user);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{login}", user.login()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(user.login()));
    }

    @Test
    public void getUserByLoginUserNotFoundReturns404() throws Exception {
        // given
        var nonExistingUser = "nonExistingUser";
        when(userService.getUserByLogin(nonExistingUser))
                .thenThrow(new IllegalStateException(String.format("User with login %s not found", nonExistingUser)));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{login}", nonExistingUser))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
