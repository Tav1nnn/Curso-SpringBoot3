package br.com.otavio.CursoSpringBoot3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAthenticationException extends AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidJwtAthenticationException(String ex) {
		// TODO Auto-generated constructor stub
		
		super(ex);
	}	
}
