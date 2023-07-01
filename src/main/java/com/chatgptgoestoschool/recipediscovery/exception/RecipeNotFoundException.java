package com.chatgptgoestoschool.recipediscovery.exception;

public class RecipeNotFoundException extends Exception {
  private int status = 400;
  private String message;

  public RecipeNotFoundException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return "Error with status code " + status + ", with message " + message;
  }
}
