package br.senac.sp.projeto.cineticketoficial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Login Inválido")
public class LoginInvalidException extends RuntimeException{
    public LoginInvalidException() {
        super("Credenciais inválidas");
    }
}
