package com.fiap.msproduct.controllers.exceptions;

/**
 * Classe personalizada de BadRequestError.
 **/
public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
