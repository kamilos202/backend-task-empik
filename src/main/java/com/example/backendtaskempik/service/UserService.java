package com.example.backendtaskempik.service;

import com.example.backendtaskempik.model.Request;
import com.example.backendtaskempik.model.User;
import com.example.backendtaskempik.repository.RequestRepository;
import com.example.backendtaskempik.util.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String GITHUB_API_BASE_USERS_URL = "https://api.github.com/users";
    private final JsonTransformer jsonTransformer;
    private final RequestRepository requestRepository;

    public UserService(RequestRepository requestRepository, JsonTransformer jsonTransformer) {
        this.requestRepository = requestRepository;
        this.jsonTransformer = jsonTransformer;
    }

    public User getUserByLogin(String login) {
        return fetchUserFromGitHub(login).orElseThrow(() -> new IllegalStateException(
                String.format("User with login %s not found", login)));
    }

    private Optional<User> fetchUserFromGitHub(String login) {
        try {
            // Build the GitHub API URL for the specified user
            URI uri = URI.create(String.format("%s/%s", GITHUB_API_BASE_USERS_URL, login));

            // Create an HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .build();

            // Create an HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();

            // Send the request and handle the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Response body: {}", response.body());
            // Parse the JSON response into a User record
            if (response.statusCode() == HttpStatus.OK.value()) {
                return jsonTransformer.deserialize(response.body(), User.class);
            } else {
                logger.error("GitHub API request failed with status code: {}", response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
           throw new IllegalStateException("Could not fetch the User from GitHub API.", e);
        }
        return Optional.empty();
    }

    public void incrementRequestCount(String login) {
        Optional<Request> requestOptional = requestRepository.findByLogin(login);
        Request request = requestOptional.orElseGet(() -> new Request(login, 1L));
        request.setRequestCount(request.getRequestCount() + 1L);
        requestRepository.save(request);
    }
}
