package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.entity.UserSession;
import br.senac.sp.projeto.cineticketoficial.repository.AcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcessoService {
    private final AcessoRepository repository;

    public List<Acesso> buscarTodosAcessos() {
        return this.repository.findAll();
    }

    public Acesso buscarAcessoPorEmail(String email) {
        return this.repository.findById(email).orElseThrow();
    }

    public Acesso atualizarSenha (Acesso acesso) {
        Acesso acessoAtualizado = buscarAcessoPorEmail(acesso.getEmail());
        acessoAtualizado.setSenha(acesso.getSenha());
        this.repository.save(acesso);
        return acesso;
    }
}
