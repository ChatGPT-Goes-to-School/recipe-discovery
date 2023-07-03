package com.chatgptgoestoschool.recipediscovery.exception;

public class RecipeNotOwnedException extends Exception {
  private int status = 400;

  public RecipeNotOwnedException(String message) {
    super(message);
  }

  public String getMessage() {
    return "Error with status code " + status + ", with message " + super.getMessage();
  }
}
