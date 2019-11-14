package com.parkson.exception;

public class ValidationMessage {
	  private final String fieldName;

	  private final String message;

	  public ValidationMessage(String fieldName, String message) {
	    this.fieldName = fieldName;
	    this.message = message;
	  }

	  public String getFieldName() {
	    return fieldName;
	  }

	  public String getMessage() {
	    return message;
	  }
}
