package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.UserSession;
import br.senac.sp.projeto.cineticketoficial.model.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.model.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcessoService {
    private AcessoRepository repository;
    private UserSession userSession;

    @Autowired
    public AcessoService(AcessoRepository repository, UserSession userSession) {
        this.repository = repository;
        this.userSession = userSession;
    }

//    public Acesso add (Acesso acesso) {
//        return this.repository.save(acesso);
//    }

//    public Iterable<Acesso> list () {
//        return this.repository.findAll();
//    }

    public UserSession getAcesso (String email, String senha) {
        Acesso login = this.repository.findByEmailAndSenha(email,senha);
        UserSession loginSession = new UserSession(login.getEmailCliente(),
                login.getSenha(), true);





//        Optional<Acesso> login = this.repository.findById(email);
        return loginSession;
    }

//    public Acesso delete (String email) {
//        Acesso deleted = getAcesso(email).get();
//        repository.deleteById(email);
//        return deleted;
//    }
}
