package br.com.fiap.auth.controller.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Classe base para as classes de error.
 **/
@Getter
@Setter
@Builder
public class StandardError {
    private final Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String message;
    private String path;

}
