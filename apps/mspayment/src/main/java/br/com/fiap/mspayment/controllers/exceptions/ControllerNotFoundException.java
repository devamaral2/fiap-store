package br.com.fiap.mspayment.controllers.exceptions;

/**
 * Classe de ControllerNotFoundException.
 **/
public class ControllerNotFoundException extends RuntimeException {
  public ControllerNotFoundException(String message) {
    super(message);
  }
}
