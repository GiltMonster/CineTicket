package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.*;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ClienteServiceTest {
    private final ClienteRepository repository = Mockito.mock(ClienteRepository.class);
    private final ClienteService service = new ClienteService(repository);

    @Test
    void testInserirCliente_Sucesso() {
        CadastroDTO cadastroDTO = new CadastroDTO();
        cadastroDTO.setEmail("cliente@teste.com");
        cadastroDTO.setNome("João");
        cadastroDTO.setSobrenome("Silva");
        cadastroDTO.setDataNascimento(LocalDate.of(1990, 10, 15));
        cadastroDTO.setTelefone("123456789");
        cadastroDTO.setEndereco("Rua Teste, 123");
        cadastroDTO.setSenha("senha123");

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

        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = service.inserirCliente(cadastroDTO);

        assertEquals(cliente.getEmail(), resultado.getEmail());
        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getSobrenome(), resultado.getSobrenome());
        assertEquals(cliente.getDataNascimento(), resultado.getDataNascimento());
        assertEquals(cliente.getTelefone(), resultado.getTelefone());
        assertEquals(cliente.getEndereco(), resultado.getEndereco());
        assertEquals(cliente.getAcesso().getEmail(), resultado.getAcesso().getEmail());
        assertEquals(cliente.getAcesso().getSenha(), resultado.getAcesso().getSenha());
    }

    @DisplayName("Atributos Nulos no Cadastro DTO")
    @Test
    void testInserirCliente_AtributosNulosNoCadastroDTO() {
        CadastroDTO cadastroDTO = new CadastroDTO();
        cadastroDTO.setEmail("cliente@teste.com");
        cadastroDTO.setNome(null);
        cadastroDTO.setSobrenome("Silva");
        cadastroDTO.setDataNascimento(LocalDate.of(1990, 10, 15));
        cadastroDTO.setTelefone("123456789");
        cadastroDTO.setEndereco("Rua Teste, 123");
        cadastroDTO.setSenha("senha123");

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

        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        assertThrows(NullAttributesException.class, () -> service.inserirCliente(cadastroDTO),
                "Todos os valores devem possuir valores");
    }

    @Test
    void testBuscarClientePorEmail_Sucesso() {
        String emailBusca = "exemplo@gmail.com";
        Cliente cliente = new Cliente();
        cliente.setEmail("exemplo@gmail.com");


        when(repository.findById(emailBusca)).thenReturn(Optional.of(cliente));

        Cliente resultado = service.buscarClientePorEmail(emailBusca);

        assertEquals(cliente,resultado);
        assertEquals(cliente.getEmail(),resultado.getEmail());
        verify(repository, times(1)).findById(emailBusca);
    }

    @Test
    void testBuscarClientePorEmail_NaoEncontrado() {
        String emailBusca = "exemplo@gmail.com";
        Cliente cliente = new Cliente();
        cliente.setEmail("exemplo2@gmail.com");

        when(repository.findById(emailBusca)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarClientePorEmail(emailBusca));
        verify(repository, times(1)).findById(emailBusca);
    }

    @Test
    void testBuscarSessaoPorId_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarClientePorEmail(null),
                "Email não pode ser nulo");
        verify(repository, times(0)).findById(null);
    }

    @Test
    void testDeleteCliente_Sucesso() {
        String email = "exemplo@gmail.com";
        Cliente cliente = new Cliente();
        cliente.setEmail(email);

        when(repository.findById(email)).thenReturn(Optional.of(cliente));

        Cliente resultado = service.deletarCliente(email);

        assertEquals(cliente, resultado);
        assertEquals(cliente.getEmail(),resultado.getEmail());
        verify(repository, times(1)).deleteById(email);

    }

    @Test
    void testDelete_IdNaoEncontrado() {
        String email = "exemplo@gmail.com";
        Cliente cliente = new Cliente();
        cliente.setEmail(email);

        when(repository.findById(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.deletarCliente(email));

        verify(repository, times(0)).deleteById(email);
    }

    @Test
    void testDelete_IdNull() {
        assertThrows(IllegalArgumentException.class, () -> service.deletarCliente(null),
                "Email não pode ser Nulo");
    }
}