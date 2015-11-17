package ru.javawebinar.topjava.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by demi
 * on 16.11.15.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Unprocessable Entity")  // 422
public class ValidationException extends RuntimeException
{
	public ValidationException(String message)
	{
		super(message);
	}
}
