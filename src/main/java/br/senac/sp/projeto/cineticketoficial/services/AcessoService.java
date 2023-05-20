package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.exceptions.InvalidEmailException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.AcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcessoService {
    private final AcessoRepository repository;
    private ValidadorEmail validadorEmail = new ValidadorEmail();

    public Acesso buscarAcessoPorEmail(String email) {
        boolean valido = validadorEmail.validate(email);
        if (!valido) {
            throw new InvalidEmailException(email);
        }
        return this.repository.findById(email)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Acesso atualizarSenha(Acesso acesso) {
        Acesso acessoAtualizado = buscarAcessoPorEmail(acesso.getEmail());
        acessoAtualizado.setSenha(acesso.getSenha());
        this.repository.save(acessoAtualizado);
        return acesso;
    }
}
