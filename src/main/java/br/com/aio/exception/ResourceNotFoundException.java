package br.com.aio.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5954144032755829053L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
