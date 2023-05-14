package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public Cliente inserirCliente(CadastroDTO cadastroDTO) {
        Cliente cliente = new Cliente();
        cliente.setEmail(cadastroDTO.getEmail());
        cliente.setNome(cadastroDTO.getNome());
        cliente.setSobrenome(cadastroDTO.getSobrenome());
        cliente.setDataNascimento(cadastroDTO.getDataNascimento());
        cliente.setTelefone(cadastroDTO.getTelefone());
        cliente.setEndereco(cadastroDTO.getEndereco());

        Acesso acesso = new Acesso();
        acesso.setEmail(cadastroDTO.getEmail());
        acesso.setSenha(cadastroDTO.getSenha());

        cliente.setAcesso(acesso);
        return this.repository.save(cliente);
    }

    public List<Cliente> buscarTodosClientes() {
        return this.repository.findAll();
    }

    public Cliente buscarClientePorEmail(String email) {
        return this.repository.findById(email).orElse(null);
    }

    public Cliente excluirCliente(String email) {
        Cliente deleted = buscarClientePorEmail(email);
        repository.deleteById(email);
        return deleted;
    }

}
