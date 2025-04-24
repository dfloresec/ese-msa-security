package ec.com.security.exception;

public class AuthenticationFailedException extends RuntimeException {

	private static final long serialVersionUID = -5272975718735152037L;

	public AuthenticationFailedException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public AuthenticationFailedException(String errorMessage) {
		super(errorMessage);
	}
}
