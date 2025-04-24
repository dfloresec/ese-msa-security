package ec.com.security.exception;

public class TokenFailedException extends RuntimeException {

	private static final long serialVersionUID = -5272975718735152037L;

	public TokenFailedException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public TokenFailedException(String errorMessage) {
		super(errorMessage);
	}
}
