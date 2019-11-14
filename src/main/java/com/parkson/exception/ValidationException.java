package com.parkson.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException {
	  private List<ValidationMessage> violations = new ArrayList<>();

	  public List<ValidationMessage> getViolations() {
	    return violations;
	  }

	  public void setViolations(List<ValidationMessage> violations) {
	    this.violations = violations;
	  }
}
