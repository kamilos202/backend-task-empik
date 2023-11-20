package com.example.backendtaskempik.service;

import com.example.backendtaskempik.model.Request;
import com.example.backendtaskempik.repository.RequestRepository;
import com.example.backendtaskempik.util.JsonTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private RequestRepository requestRepository;
    @MockBean
    private JsonTransformer jsonTransformer;

    @Test
    public void incrementRequestCount_IncrementsInDatabase() {
        // given
        UserService userService = new UserService(requestRepository, jsonTransformer);
        var login = "testUser";

        // sanity check
        assertThat(requestRepository.findByLogin(login)).isEmpty();

        // when
        userService.incrementRequestCount(login); // increment by one

        // when & then
        Optional<Request> requestOptional = requestRepository.findByLogin(login); // find and increment by one
        assertThat(requestOptional).isPresent();
        Request request = requestOptional.get();
        assertThat(request.getRequestCount()).isEqualTo(2L);

        // when
        userService.incrementRequestCount(login); // increment by one

        // when & then // involves finding and incrementing by one
        assertThat(requestRepository.findByLogin(login)).map(Request::getRequestCount).contains(3L);
    }
}
