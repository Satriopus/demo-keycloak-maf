package br.com.almarsantana.demokeycloakmfa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.micrometer.common.lang.NonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${keycloack.url-login}")
    @NonNull
    private String URL_KEYCLOAK;
    @Value("${keycloack.grant-type}")
    private String GRANT_TYPE;
    @Value("${keycloack.client-id}")
    private String CLIENT_ID;

    private final RestTemplate restTemplate;

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credential credential) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", GRANT_TYPE);
        map.add("client_id", CLIENT_ID);
        map.add("username", credential.username);
        map.add("password", credential.password);
        map.add("totp", credential.otp);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL_KEYCLOAK, entity, String.class);

        return response;
    }

    public record Credential(String username, String password, String otp) {
    }
}
