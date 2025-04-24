package ec.com.security.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.security.service.AuthenticationService;
import ec.com.security.service.dto.RequestTokenDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@GetMapping(value = "/token")
	@ResponseStatus(HttpStatus.OK)
	private Map<String, String> generateToken(@RequestBody RequestTokenDto request) {
		return authenticationService.generateToken(request);
	}

	@PostMapping(value = "/validateToken")
	@ResponseStatus(HttpStatus.OK)
	private void validateToken(@RequestHeader("Authorization") String bearerToken,
			@RequestHeader("clientSecret") String clientSecret) {
		authenticationService.validateToken(bearerToken, clientSecret);
	}

}
