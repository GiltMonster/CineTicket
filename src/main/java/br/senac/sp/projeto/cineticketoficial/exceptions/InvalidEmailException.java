package br.senac.sp.projeto.cineticketoficial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email inválido: ")
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super("Email inválido: " + message);
    }
}
