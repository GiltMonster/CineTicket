package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente inserirCliente(CadastroDTO cadastroDTO) {
        Cliente cliente = criarClienteAPartirDTO(cadastroDTO);
        return this.repository.save(cliente);
    }

    private Cliente criarClienteAPartirDTO(CadastroDTO cadastroDTO) {
        if (cadastroDTO.possuiAtributosNulos()) {
            throw new NullAttributesException();
        }
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
        return cliente;
    }

    //não é necessario ter
    public List<Cliente> buscarTodosClientes() {
        return this.repository.findAll();
    }

    public Cliente buscarClientePorEmail(String email) {
        if (email == null) {
            throw  new IllegalArgumentException("Email não pode ser Nulo");
        }
        return this.repository.findById(email).orElseThrow(ResourceNotFoundException::new);
    }

    public Cliente deletarCliente(String email) {
        if (email == null) {
            throw  new IllegalArgumentException("Email não pode ser Nulo");
        }
        Cliente deleted = buscarClientePorEmail(email);
        repository.deleteById(email);
        return deleted;
    }

}
