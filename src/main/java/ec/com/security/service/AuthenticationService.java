package ec.com.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import ec.com.security.exception.AuthenticationFailedException;
import ec.com.security.exception.TokenFailedException;
import ec.com.security.service.dto.RequestTokenDto;
import ec.com.security.service.dto.UserDto;
import ec.com.security.util.Util;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private static final String TOKEN_PREFIX = "Bearer ";

	@Value("${key}")
	private String key;

	@Value("${client_secret}")
	private String clientSecret;

	@Value("${token_expiration_time}")
	private int expirationTime;

	private final UserService userService;

	public Map<String, String> generateToken(RequestTokenDto request) {
		Map<String, String> map = new HashMap<>();
		if (!clientSecret.equals(request.getClientSecret())) {
			throw new AuthenticationFailedException("50 Credenciales incorrectas. ClientSecret no válido");
		}
		UserDto user = userService.getUser(request.getUser());
		if (user == null) {
			throw new AuthenticationFailedException("51 Credenciales incorrectas. User no existe");
		}
		if (!user.getPassword().equals(request.getPassword())) {
			throw new AuthenticationFailedException("52 Credenciales incorrectas. Password no coincide");
		}
		map.put("token",
				JWT.create().withSubject(user.getUsermane())
						.withExpiresAt(Util.addSecondsToDate(new Date(), expirationTime))
						.sign(Algorithm.HMAC512(key.getBytes())));
		return map;
	}

	public void validateToken(String token, String clientSecret) {
		if (token == null) {
			throw new TokenFailedException("60 Token required");
		}
		try {
			DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(key.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, ""));

			if (!clientSecret.equals(clientSecret)) {
				throw new TokenFailedException("61 Unauthorized Token. ClientSecret no válido");
			}
			UserDto user = userService.getUser(decodedJWT.getSubject());
			if (user == null) {
				throw new TokenFailedException("62 Unauthorized Token. User no existe");
			}
		} catch (Exception e) {
			throw new TokenFailedException("64 Unauthorized Token. " + e.getMessage());
		}

	}

}
