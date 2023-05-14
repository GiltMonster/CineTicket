package br.senac.sp.projeto.cineticketoficial.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Optional;

// mexer depois
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@RequiredArgsConstructor
public class UserSession {
    private String email;
    private String senha;
    private boolean isLogado = false;

    public UserSession(String email, String senha, boolean isLogado) {
        this.email = email;
        this.senha = senha;
        this.isLogado = isLogado;
    }

    public boolean iniciarSession(Optional<Acesso> acesso) {
        this.email= acesso.get().getEmail();
        this.senha = acesso.get().getEmail();
        return this.isLogado = true;
    }
}
