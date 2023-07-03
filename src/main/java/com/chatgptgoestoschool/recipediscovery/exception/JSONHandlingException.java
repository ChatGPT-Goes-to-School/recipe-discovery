package com.chatgptgoestoschool.recipediscovery.exception;

public class JSONHandlingException extends Exception {
  private int status = 400;

  public JSONHandlingException(String message) {
    super(message);
  }

  public String getMessage() {
    return "Error with status code " + status + ", with message " + super.getMessage();
  }

  public int getStatus() {
    return status;
  }
}
