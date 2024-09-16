package br.com.fiap.mspayment.controllers.exceptions;

/**
 * Classe personalizada de BadRequestError.
 **/
public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
    System.out.println("É nós aqui mesmossssss");
    System.out.println(message);
  }
}
