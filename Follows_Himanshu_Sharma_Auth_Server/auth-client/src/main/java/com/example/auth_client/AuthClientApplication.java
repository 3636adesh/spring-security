package com.example.auth_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@SpringBootApplication
public class AuthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthClientApplication.class, args);
	}

}

@RestController
class MessageController {

	private final OAuth2AuthorizedClientService authorizedClientService;

	public MessageController(OAuth2AuthorizedClientService authorizedClientService) {
		this.authorizedClientService = authorizedClientService;
	}

	@GetMapping("/message")
	public String message(Principal principal) {
		var restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		String accessToken = authorizedClientService
				.loadAuthorizedClient("reg-client", principal.getName())
				.getAccessToken().getTokenValue();
		httpHeaders.set("Authorization", "Bearer " + accessToken);

		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		ResponseEntity<String> response =
				restTemplate.exchange("http://localhost:8181/hello", HttpMethod.GET, entity, String.class);

		return "Success  :: " + response.getBody();
	}
}


