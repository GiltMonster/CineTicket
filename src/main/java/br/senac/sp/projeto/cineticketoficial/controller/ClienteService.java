package br.senac.sp.projeto.cineticketoficial.controller;

import br.senac.sp.projeto.cineticketoficial.model.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService  {
    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente add(Cliente cliente) {
//        cliente.getAcesso().setEmail(cliente.getEmail());
//        cliente.getAcesso().setSenha(cliente.getSenha());

        Cliente clienteAdd = this.repository.save(cliente);
        return clienteAdd;
    }

    public Iterable<Cliente> list() {
        return this.repository.findAll();
    }

    public Optional<Cliente> getCliente(String email) {
        return this.repository.findById(email);
    }

    public Cliente delete(String email) {
        Cliente deleted = getCliente(email).get();
        repository.deleteById(email);
        return deleted;
    }

}
