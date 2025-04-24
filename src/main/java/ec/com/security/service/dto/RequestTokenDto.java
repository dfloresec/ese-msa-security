package ec.com.security.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestTokenDto {

	@JsonProperty("user")
	private String user;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("clientSecret")
	private String clientSecret;
	
}
