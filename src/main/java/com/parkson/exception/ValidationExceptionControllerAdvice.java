package com.parkson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationExceptionControllerAdvice {
	  //@ExceptionHandler(ConstraintViolationException.class)
//	  @ResponseStatus(HttpStatus.BAD_REQUEST)
//	  @ResponseBody
//	  ValidationErrorResponse onConstraintValidationException(
//	      ConstraintViolationException e) {
//	    ValidationErrorResponse error = new ValidationErrorResponse();
//	    for (ConstraintViolation violation : e.getConstraintViolations()) {
//	      error.getViolations().add(
//	        new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
//	    }
//	    return error;
//	  }

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  ValidationException onMethodArgumentNotValidException(
	      MethodArgumentNotValidException e) {
		  ValidationException error = new ValidationException();
	    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
	      error.getViolations().add(
	        new ValidationMessage(fieldError.getField(), fieldError.getDefaultMessage()));
	    }
	    return error;
	  }
	  
}
