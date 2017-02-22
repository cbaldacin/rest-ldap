package com.restldap.api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The employee informed has not been found")
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6784672501286896217L;

}
